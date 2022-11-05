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

      // print line for this order
      builder.append(String.format("  %s: %s (%s seats)\n", play.getName(), frmt.format(thisAmount), perf.audience));
      totalPrice += thisAmount;
    }

    builder.append(String.format("Amount owed is %s\n", frmt.format(totalPrice)));
    builder.append(String.format("You earned %s credits\n", nbPtsFidelity.intValue()));
    writeToFile("output/Facture.txt", builder.toString());

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
