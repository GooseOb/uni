public class Bird extends Animal {
  public Bird(Float weight, int age, String name) {
    super(weight, age, name);
  }

  @Override
  protected String getSpecie() {
    return "Bird";
  }

  public void fly() {
    System.out.println("Flying");
  }
}
