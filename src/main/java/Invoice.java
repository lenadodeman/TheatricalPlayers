import java.text.NumberFormat;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
public class Invoice {

  public Customer customer;
  public List<Performance> performances;
  static final String tragedy = "tragedy";
  static final String comedy = "comedy";
  private Double totalPrice = 0.0;

  public Invoice(Customer customer, List<Performance> performances) {
    this.customer = customer;
    this.performances = performances;
  }

  public String printToText(){
    totalPrice = 0.0;
    Double nbPtsFidelity = 0.0;
    StringBuilder builder = new StringBuilder().append(String.format("Statement for %s\n", this.customer.getName()));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    for (Performance perf : this.performances) {
      Play play = perf.play;
      Double thisAmount = play.calculOfPrice(perf.audience);
      nbPtsFidelity += play.calculOfFidelity(perf.audience);

      builder.append(String.format("  %s: %s (%s seats)\n", play.getName(), frmt.format(thisAmount), perf.audience));
      totalPrice += thisAmount;
    }

    builder.append(String.format("Amount owed is %s\n", frmt.format(totalPrice)));
    builder.append(String.format("You earned %s credits\n", nbPtsFidelity.intValue()));
    writeToFile("output/Facture.txt", builder.toString());

    return builder.toString();
  }

  public String printToHTML() {
    totalPrice = 0.0;
    Double volumeCredits = 0.0;
    StringBuilder builder = new StringBuilder().append(String.format("<!DOCTYPE HTML>\n<html>\n<body>\n<h1>Invoice</h1>\n<ul><li><p><b>Client : </b>%s</p></li></ul>", this.customer.getName()));
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    builder.append(String.format("<table style=\"border: 1px solid black\"><thead><tr><th style=\"border: 1px solid black\"><b>Piece</b></th><th style=\"border: 1px solid black\"><b>Seats sold</b></th><th style=\"border: 1px solid black\"><b>Price</b></th></tr></thead><tbody>"));
    for (Performance perf : this.performances) {
      totalPrice += perf.getPrice(customer);

      volumeCredits += perf.getFidelity();
      customer.updateFidelity(perf);

      builder.append(String.format("<tr><td style=\"border: 1px solid black\">%s</td><td style=\"border: 1px solid black\">%s</td><td style=\"border: 1px solid black\">%s</td></tr>", perf.play.getName(), perf.stringAudience(), frmt.format(perf.getPrice(customer))));
      if (perf.getDiscount()) {
        builder.append(String.format("<tr><td align=\"right\" colspan=\"2\"><b>Discount: (already applied)</b></td><td>%s</td></tr>", frmt.format((-15))));
      }
    }
    builder.append(String.format("<tr><td align=\"right\" style=\"border: 1px solid black\" colspan=\"2\"><b>Total owned:</b></td><td style=\"border: 1px solid black\">%s</td></tr><td align=\"right\" style=\"border: 1px solid black\" colspan=\"2\"><b>Fidelity points earned:</b></td><td style=\"border: 1px solid black\">%d</td><tr></tr></tbody></table>", frmt.format(totalPrice), volumeCredits.intValue()));
    builder.append("<p><i>Payment is required under 30 days. We can break your knees if you don't do so.</i></p></body></html>");
    writeToFile("output/Invoice.html", builder.toString());

    return builder.toString();
  }

  public void writeToFile(String fileName, String output) {
    try{
      FileWriter writer = new FileWriter(fileName);
      writer.write(output);
      writer.close();
    }catch(IOException e){
      System.err.println(e.getMessage());
    }
  }

}
