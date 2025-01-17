public class Elephant extends Mammal {
  public Elephant(Float weight, int age, String name, int numberOfLegs) {
    super(weight, age, name, numberOfLegs);
  }

  @Override
  protected String getSpecie() {
    return "Elephant";
  }
}
