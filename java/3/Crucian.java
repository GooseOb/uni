public class Crucian extends Fish implements Carnivorous {
  public Crucian(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Crucian";
  }
}
