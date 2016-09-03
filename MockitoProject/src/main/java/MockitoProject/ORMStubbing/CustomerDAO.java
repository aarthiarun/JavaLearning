package MockitoProject.ORMStubbing;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by arunmohzi on 9/1/16.
 */
public class CustomerDAO {
    @PersistenceContext
    EntityManager em;

    public CustomerDAO(EntityManager em) {
        this.em = em;
    }

    public Optional<Customer> findById(long id) throws Exception {
        return Optional.ofNullable(em.find(Customer.class, id));
    }

    public List<Customer> findAll() throws Exception {
        TypedQuery<Customer> query = em.createQuery("select * from CUSTOMER", Customer.class);
        return query.getResultList();
    }

    public Customer update(Customer customer) throws Exception {
        return em.merge(customer);
    }



}
