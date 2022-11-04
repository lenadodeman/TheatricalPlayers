import java.text.NumberFormat;
import java.util.*;

public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  static final String tragedy = "tragedy";
  static final String comedy = "comedy";
  private Double totalAmount = 0.0;

  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public String print(ArrayList<Play> plays) {
    totalAmount = 0.0;
    Double volumeCredits = 0.0;

    StringBuilder builder = new StringBuilder().append(String.format("Statement for %s\n", this.customer.getName()));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : this.performances) {
      Play play = perf.play;
      Double thisAmount = play.calculOfAmount(perf.audience);
      volumeCredits += play.calculOfCredits(perf.audience);

      // print line for this order
      builder.append(String.format("  %s: %s (%s seats)\n", play.getName(), frmt.format(thisAmount), perf.audience));
      totalAmount += thisAmount;
    }

    builder.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    builder.append(String.format("You earned %s credits\n", volumeCredits.intValue()));
    return builder.toString();
  }
}
