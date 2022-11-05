public class Performance {

  public Play play;
  public int audience;
  private Double ptsFidelity = 0.0;
  private Double price = 0.0;
  private boolean discount = false;

  public Performance(Play play, int audience) {
    this.play = play;
    this.audience = audience;
    updateDiscount();
  }

  public String stringAudience() {
    return  String.valueOf(audience);
  }

  public Double getFidelity(){
    return ptsFidelity;
  }

  private void setFidelity(Double ptsFidelity){
    this.ptsFidelity = ptsFidelity;
  }

  private void setPrice(Double price){
    this.price = price;
  }

  public Double getPrice(Customer customer){
    updatePrice(customer);
    return price;
  }

  public void updatePrice(Customer customer) {
    Double amount = play.calculOfPrice(this.audience);
    if (customer.possibleDiscount()) {
      amount -= 15;
    }
    setPrice(amount);
  }

  public void updateDiscount(){
    setFidelity(play.calculOfFidelity(this.audience));
  }
  public boolean getDiscount(){
    return discount;
  }

}
