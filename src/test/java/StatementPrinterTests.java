import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {

        Customer customer = new Customer("BigCo");

        ArrayList<Play> plays = new ArrayList<>();
        plays.add(new Tragedy("Hamlet"));
        plays.add(new Comedy("As You Like It"));
        plays.add(new Tragedy("Othello"));

        Invoice invoice = new Invoice(customer, List.of(
                new Performance(plays.get(0), 55),
                new Performance(plays.get(1), 35),
                new Performance(plays.get(2), 40)));

        var result = invoice.print(plays);

        verify(result);
    }

}
