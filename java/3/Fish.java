public class Fish extends Animal {
  public Fish(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Fish";
  }

  public void swim() {
    System.out.println("Swimming");
  }
}
