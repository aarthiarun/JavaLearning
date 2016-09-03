package MockitoProject;

import MockitoProject.PrinterWriter.Printer;
import MockitoProject.PrinterWriter.PrinterNotConnectedException;
import MockitoProject.PrinterWriter.StringProcessor;
import MockitoProject.PrinterWriter.SysoutPrinter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;


/**
 * Created by arunmohzi on 9/1/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class StringProcessorTest {

    //1. Basic Mock object
    @Mock
    private Printer printer;
    @Test
    public void internal_buffer_should_be_absent_after_construction() {
        StringProcessor processor = new StringProcessor(printer);
        Optional<String> actualBuffer = null;
        try {
            actualBuffer = processor.statusAndTest();
        } catch (PrinterNotConnectedException e) {
            e.printStackTrace();
        }
        assertFalse(actualBuffer.isPresent());
    }

    //2. Basic Spy object to execute the actual method
    @Spy
    private SysoutPrinter sysoutPrinter;
    @Test
    public void internal_buffer_should_be_absent_after_construction_sysout() {
        StringProcessor processor = new StringProcessor(sysoutPrinter);
        Optional<String> actualBuffer = null;
        try {
            actualBuffer = processor.statusAndTest();
        } catch (PrinterNotConnectedException e) {
            e.printStackTrace();
        }
        assertFalse(actualBuffer.isPresent());
    }

    //2. Basic Spy object with some stubbing
    @Test
    public void internal_buffer_should_be_absent_after_construction_sysout_with_donothing(){
        StringProcessor processor = new StringProcessor(sysoutPrinter);
        doNothing().when(sysoutPrinter).printTestPage();
        Optional<String> actualBuffer = null;
        try {
            actualBuffer = processor.statusAndTest();
        } catch (PrinterNotConnectedException e) {
            e.printStackTrace();
        }
        assertFalse(actualBuffer.isPresent());
    }


    @Test(expected = PrinterNotConnectedException.class)
    public void printer_not_connected_exception_should_be_thrown_up_the_stack() throws Exception {
        StringProcessor processor = new StringProcessor(printer);
        doThrow(new PrinterNotConnectedException()).when(printer).printTestPage();
        Optional<String> actualBuffer = processor.statusAndTest();
        assertFalse(actualBuffer.isPresent());
    }

}
