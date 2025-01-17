public class Owl extends Bird implements Carnivorous {
  public Owl(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Owl";
  }
}
