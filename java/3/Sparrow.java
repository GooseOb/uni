public class Sparrow extends Bird {
  public Sparrow(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Sparrow";
  }
}
