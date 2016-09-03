package MockitoProject;

import MockitoProject.PrinterWriter.Printer;
import MockitoProject.PrinterWriter.PrinterDiagnostics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

/**
 * Created by arunmohzi on 9/3/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrinterDiagnosticsTest {
    private PrinterDiagnostics diagnostics;
    @Mock
    private Printer printer;
    @Captor
    private ArgumentCaptor<String> textCaptor;
    @Before
    public void setUp() throws Exception {
        diagnostics = new PrinterDiagnostics(printer);
    }


    @Test
    public void verify_diagnostic_information_added_to_text() {
        // Given
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Integer copies = 3;
        Boolean collate = true;
        String expectedCopies = "Copies: " + copies;
        String expectedCollate = "Collate: " + collate;

        // When
        diagnostics.diagnosticPrint(text, copies, collate);

        // Then
        verify(printer).print(textCaptor.capture(), eq(copies), eq(collate));
        assertTrue(textCaptor.getValue().contains(expectedCopies));
        assertTrue(textCaptor.getValue().contains(expectedCollate));
        assertTrue(textCaptor.getValue().contains(text));
    }


}
