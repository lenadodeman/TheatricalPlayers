public abstract class Play {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract Double calculOfPrice(Integer audience);
  public abstract Double calculOfFidelity(Integer audience);

}
