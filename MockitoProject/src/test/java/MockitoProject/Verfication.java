package MockitoProject;

import MockitoProject.PrinterWriter.Printer;
import MockitoProject.PrinterWriter.PrinterNotConnectedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.concurrent.Executors;

import static org.mockito.Mockito.*;


/**
 * Created by arunmohzi on 9/3/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class Verfication {
    @Mock
    private Printer printer;

    @Test
    public void simple_interaction_verification() throws PrinterNotConnectedException {
        printer.printTestPage();


//        verify(printer, times(3)).printTestPage();
        verify(printer, atLeastOnce()).printTestPage();
        // Other keywords --> atLeast(2), atMost(3), never()

//        printer.turnOff();
//        verify(printer, only()).printTestPage();


    }


    @Test
    public void verification_with_actual_parameters_and_verification_mode() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);

        // Then
        verify(printer, never()).print(text2, copies, collate);
    }


    @Test
    public void verification_with_matchers() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        String text2 = "Ut enim ad minim veniam, quis nostrud exercitation ullamco "
                + "laboris nisi ut aliquip ex ea commodo consequat.";
        Integer copies3 = 3;
        Integer copies4 = 4;
        Boolean doCollate = true;
        Boolean doNotCollate = false;

        // When
        printer.print(text, copies3, doCollate);
        printer.print(text2, copies4, doNotCollate);

        // Then
        verify(printer, times(2)).print(anyString(), anyInt(), anyBoolean());
    }

    // failing test case
    @Test
    public void verification_with_mixed_matchers_fails() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies5 = 5;
        Integer copies10 = 10;
        Boolean collate = true;

        // When
        printer.print(text, copies10, collate);

        // Then
        verify(printer).print(anyString(), eq(copies5), eq(collate));
    }

    //Timedout Testcase --Failing case
    @Test
    public void verification_with_timeout_fails() throws InterruptedException, PrinterNotConnectedException {
        Executors.newFixedThreadPool(1).execute(() -> {
            try {
                printTestWithSleep();
            } catch (PrinterNotConnectedException e) {
                e.printStackTrace();
            }
        });
        verify(printer, timeout(100).times(1)).printTestPage();
    }

    private void printTestWithSleep() throws PrinterNotConnectedException {
        try {
            Thread.sleep(200L);
            printer.printTestPage();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //failing test case -- verifyNoMoreInteractions
    @Test
    public void verify_no_more_interactions_fails() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;

        // When
        printer.print(text, copies, collate);
        printer.turnOff();

        // Then
        verify(printer).print(text, copies, collate);
        verifyNoMoreInteractions(printer);
    }


    //failing test case
    @Test
    public void verify_in_order_fails() throws PrinterNotConnectedException {
        // Given
        InOrder inOrder = Mockito.inOrder(printer);

        // When
        printer.turnOff();
        printer.printTestPage();

        // Then
        inOrder.verify(printer).printTestPage();
        inOrder.verify(printer).turnOff();
    }


}
