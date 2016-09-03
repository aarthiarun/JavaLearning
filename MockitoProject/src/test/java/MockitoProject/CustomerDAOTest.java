package MockitoProject;

import MockitoProject.ORMStubbing.Customer;
import MockitoProject.ORMStubbing.CustomerDAO;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * Created by arunmohzi on 9/1/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerDAOTest {
    private CustomerDAO dao;
    @Mock
    private EntityManager mockEntityManager;

    @Before
    public void setUp() throws Exception {
        dao = new CustomerDAO(mockEntityManager);
        setupCustomers();
    }

    @Test
    public void finding_existing_customer_should_return_customer() throws Exception {
        long expectedId = 10;
        String expectedName = "John Doe";
        String expectedAddress = "21 Main Street";
        Customer expectedCustomer = new Customer(expectedId, expectedName, expectedAddress);

        when(mockEntityManager.find(Customer.class,expectedId)).thenReturn(expectedCustomer);
        //when(mockEntityManager.find(Customer.class, expectedId)).thenReturn(expectedCustomer1, expectedCustomer2);
        Optional<Customer> actualCustomer = dao.findById(expectedId);

        assertTrue(actualCustomer.isPresent());
        assertEquals(expectedId, actualCustomer.get().getId());
        assertEquals(expectedName, actualCustomer.get().getName());
        assertEquals(expectedAddress, actualCustomer.get().getAddress());
    }

    @Test
    public void finding_missing_customer_should_return_null() throws Exception {
        long expectedId2 = 20;
        when(mockEntityManager.find(Customer.class,expectedId2)).thenReturn(null);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId2);
        assertFalse(actualCustomer2.isPresent());
    }

    @Test(expected=IllegalArgumentException.class)
    public void finding_customer_should_throw_exception_up_the_stack() throws Exception {
        long expectedId = 10L;
        when(mockEntityManager.find(Customer.class, expectedId)).thenThrow(new IllegalArgumentException());
        dao.findById(expectedId);
        fail("Exception should be thrown.");
    }

    //Using Answers
    private Customer homerSimpson, bruceWayne, tyrionLannister,homerSimpsonUpdated;

    private void setupCustomers() {
        homerSimpson = new Customer(1, "Homer Simpson", "Springfield");
        bruceWayne = new Customer(2, "Bruce Wayne", "Gotham City");
        tyrionLannister = new Customer(2, "Tyrion Lannister", "Kings Landing");
        homerSimpsonUpdated = new Customer(1, "Arunmozhi", "Springfield");
    }

    private Answer<Customer> withCustomerById  = new Answer<Customer>() {
        @Override
        public Customer answer(InvocationOnMock invocationOnMock) throws Throwable {
            Object[] args = invocationOnMock.getArguments();
            int id = ((Long) args[1]).intValue(); // Cast to int for switch.
            switch (id) {
                case 1:
                    return homerSimpson;
                case 2:
                    return bruceWayne;
                case 3:
                    return tyrionLannister;
                default:
                    return null;
            }
        }
    };

    @Test
    public void finding_customer_by_id_returns_appropriate_customer() throws Exception {
        long[] expectedId = {1, 2, 3};
        when(mockEntityManager.find(eq(Customer.class), anyLong())).thenAnswer(withCustomerById);

        Optional<Customer> actualCustomer0 = dao.findById(expectedId[0]);
        Optional<Customer> actualCustomer1 = dao.findById(expectedId[1]);
        Optional<Customer> actualCustomer2 = dao.findById(expectedId[2]);
        assertEquals("Homer Simpson", actualCustomer0.get().getName());
        assertEquals("Bruce Wayne", actualCustomer1.get().getName());
        assertEquals("Tyrion Lannister", actualCustomer2.get().getName());
    }

    //Using multiple mocks
    @Mock
    private TypedQuery<Customer> mockQuery;
    @Test
    public void finding_all_customers_should_return_all_customers() throws Exception {
        given(mockQuery.getResultList()).willAnswer(i -> Arrays.asList(homerSimpson, bruceWayne, tyrionLannister));
        // or given(mockQuery.getResultList()).willReturn(Arrays.asList(homerSimpson, bruceWayne, tyrionLannister));
        given(mockEntityManager.createQuery(anyString(), eq(Customer.class))).willReturn(mockQuery);

        List<Customer> actualCustomer = dao.findAll();
        assertEquals(actualCustomer.size(),3);
    }

    @Test
    public void update_customer_object() throws Exception {
        given(mockEntityManager.merge(homerSimpson)).willReturn(homerSimpsonUpdated);
        Customer updatedCustomer = dao.update(homerSimpson);
        TestCase.assertEquals(homerSimpsonUpdated.getName(), "Arunmozhi");
    }

    //Argument Matcher


}
