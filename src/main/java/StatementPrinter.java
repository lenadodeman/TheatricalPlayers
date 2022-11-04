import java.text.NumberFormat;
import java.util.*;

// Il faut qu'on utilise un StringBuilder car un seul thread

public class StatementPrinter {

  static final String tragedy = "tragedy";
  static final String comedy = "comedy";

  public String print(Invoice invoice, Map<String, Play> plays) {
    int totalAmount = 0;
    int volumeCredits = 0;
    StringBuilder builder = new StringBuilder().append(String.format("Statement for %s\n", invoice.customer));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = 0;

      switch (play.type) {
        case tragedy:
          thisAmount = 40000;
          if (perf.audience > 30) {
            thisAmount += 1000 * (perf.audience - 30);
          }
          break;
        case comedy:
          thisAmount = 30000;
          if (perf.audience > 20) {
            thisAmount += 10000 + 500 * (perf.audience - 20);
          }
          thisAmount += 300 * perf.audience;
          break;
        default:
          throw new Error("unknown type: ${play.type}");
      }

      // add volume credits
      volumeCredits += Math.max(perf.audience - 30, 0);
      // add extra credit for every ten comedy attendees
      if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

      // print line for this order
      builder.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience));
      totalAmount += thisAmount;
    }

    builder.append(String.format("Amount owed is %s\n", frmt.format(totalAmount / 100)));
    builder.append(String.format("You earned %s credits\n", volumeCredits));
    return builder.toString();
  }

}
