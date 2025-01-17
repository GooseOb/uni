public class Shark extends Fish implements Carnivorous {
  public Shark(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Shark";
  }
}
