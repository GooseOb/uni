public class Mammal extends Animal {
  public Mammal(Float weight, int age, String name, int numberOfLegs) {
    super(weight, age, name);
    this.numberOfLegs = numberOfLegs;
  }

  @Override
  protected String getSpecie() {
    return "Mammal";
  }

  public int numberOfLegs;

  public void walk() {
    System.out.println("Walking");
  }
}
