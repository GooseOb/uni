public class MainProgram {
  public static void main(String[] args) {
    Animal animal = new Tiger(10.0f, 3, "Tiger", 90);
    Fish fish = new Shark(0.5f, 1, "Shark");
    Mammal mammal = new Elephant(100.0f, 10, "Elephant", 4);
    Bird bird = new Owl(1.0f, 2, "Owl");
    Carnivorous carnivorous = new Shark(100.0f, 10, "Shark");

    animal.whoAmI();
    animal.eat();
    animal.sleep();

    fish.whoAmI();
    fish.eat();
    fish.sleep();
    fish.swim();

    mammal.whoAmI();
    mammal.eat();
    mammal.sleep();
    mammal.walk();

    bird.whoAmI();
    bird.eat();
    bird.sleep();
    bird.fly();

    carnivorous.hunt();
  }
}
