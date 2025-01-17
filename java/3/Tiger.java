public class Tiger extends Mammal implements Carnivorous {
  public Tiger(Float weight, int age, String name, int numberOfLegs) {
    super(weight, age, name, numberOfLegs);
  }

  @Override
  protected String getSpecie() {
    return "Tiger";
  }
}
