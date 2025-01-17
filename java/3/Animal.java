public class Animal {
  public Animal(Float weight, int age, String name) {
    this.weight = weight;
    this.age = age;
    this.name = name;
  }

  public Float weight;
  public int age;
  public String name;

  public void eat() {
    System.out.println("Eating");
  }

  public void sleep() {
    System.out.println("Sleeping");
  }

  protected String getSpecie() {
    return "Animal";
  }

  public void whoAmI() {
    System.out.println("name: " + name);
    System.out.println("age: " + age);
    System.out.println("weight: " + weight);
    System.out.println("specie: " + getSpecie());
    System.out.println();
  }
}
