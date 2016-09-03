package MockitoProject.PrinterWriter;

/**
 * Created by arunmohzi on 9/3/16.
 */
public class PrinterDiagnostics {
    private Printer printer;
    public PrinterDiagnostics(Printer printer) {
        this.printer = printer;
    }
    public void diagnosticPrint(String text, Integer copies, Boolean collate) {
        StringBuilder diagnostic = new StringBuilder();
        diagnostic.append("** Diagnostic Print **\\n");
        diagnostic.append("*** Copies: ").append(copies).append(" ***\\n");
        diagnostic.append("*** Collate: ").append(collate).append(" ***\\n");
        diagnostic.append("********************\\n\\n");
        printer.print(new StringBuilder().append(diagnostic).append(text).toString(), copies, collate);
    }
}
