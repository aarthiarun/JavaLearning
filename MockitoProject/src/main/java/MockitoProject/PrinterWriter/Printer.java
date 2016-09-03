package MockitoProject.PrinterWriter;

/**
 * Created by arunmohzi on 9/1/16.
 */
public interface Printer {
    void printTestPage() throws PrinterNotConnectedException;
    void turnOff();
    void print(String text, Integer copies, Boolean collate);
}
