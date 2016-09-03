package MockitoProject.PrinterWriter;

/**
 * Created by arunmohzi on 9/1/16.
 */
public class SysoutPrinter implements Printer {
    @Override
    public void printTestPage() {
        System.out.println("This is a test page");
    }

    @Override
    public void turnOff() {
    }

    @Override
    public void print(String text, Integer copies, Boolean collate) {

    }
}
