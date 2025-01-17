public class Main {
  public static void main(String[] args) {
    Shop shop = new Shop();

    shop.addProduct(new Product(ProductType.FRUIT, "Jabłko", 1.00, 53));
    shop.addProduct(new Product(ProductType.VEGETABLE, "Marchew", 0.80, 30));
    shop.addProduct(new Product(ProductType.OTHER, "Czekolada", 4.50, 20));

    System.out.println("Suma wartości produktów: " + shop.calculateTotalValue() + " zł");

    System.out.println("Opisy produktów:");
    shop.createProductDescriptions().forEach(System.out::println);

    shop.reduceFruitPrices();
    System.out.println("Po obniżce cen owoców:");
    shop.getProducts().forEach(System.out::println);

    System.out.println("Produkty poniżej 1 zł:");
    shop.getAndSortProductsUnderPrice(1.0).forEach(System.out::println);

    System.out.println("Mapa ilości produktów:");
    shop.mapProductQuantities()
        .forEach((name, quantity) -> System.out.println(name + " - " + quantity + " sztuk"));

    System.out.println("Czy jest warzywo kończące się na 'a': " + shop.checkVegetableEndsWithA());

    System.out.println();

    System.out.println("Wynik sumy: " + Utils.calculateSpecialSum());
    System.out.println("Średnia z pierwszych 5 liczb: " + Utils.randomNumbersAverage());
    System.out.println("Średnia 1000 losowych liczb: " + Utils.calculateAverageOfRandomDoubles());
    System.out.println("Losowe litery: " + Utils.generateRandomLetters());
  }
}
