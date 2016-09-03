package MockitoProject.PrinterWriter;

import java.util.Optional;

/**
 * Created by arunmohzi on 9/1/16.
 */
public class StringProcessor {
    private Printer printer;
    private String currentBuffer;

    public StringProcessor(Printer printer){
        this.printer = printer;
    }

    public Optional<String> statusAndTest() throws PrinterNotConnectedException {
        printer.printTestPage();
        return Optional.ofNullable(currentBuffer);
    }
}
