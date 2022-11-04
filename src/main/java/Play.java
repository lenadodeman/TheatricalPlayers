public abstract class Play {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public abstract Double calculOfAmount(Integer audience);
  public abstract Double calculOfCredits(Integer audience);

}
