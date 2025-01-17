public class Main {
  public static void main(String[] args) {
    Shop shop = new Shop();

    shop.addProduct(new Product(ProductType.FRUIT, "Jabłko", 1.00, 53));
    shop.addProduct(new Product(ProductType.VEGETABLE, "Marchew", 0.80, 30));
    shop.addProduct(new Product(ProductType.OTHER, "Czekolada", 4.50, 20));

    double priceThreshold = 0.9;
    System.out.println("Produkty o cenie wyższej niż " + priceThreshold + " zł:");
    shop.doOnProductList(
        product -> {
          if (product.getPrice() > priceThreshold) {
            System.out.println(product);
          }
        });

    double percentageIncrease = 10.0;
    System.out.println("\nPodwyższanie cen owoców o " + percentageIncrease + "%:");
    shop.doOnProductList(
        product -> {
          if (product.getType() == ProductType.FRUIT) {
            double newPrice = product.getPrice() * (1 + percentageIncrease / 100);
            product.setPrice(newPrice);
            System.out.println("Nowa cena produktu " + product.getName() + ": " + newPrice + " zł");
          }
        });

    System.out.println("\nŁączna wartość netto produktów:");
    shop.doOnProductList(
        product -> {
          double totalNetValue = product.getNetPrice() * product.getQuantity();
          System.out.printf("Łączna wartość netto %s: %.2f zł%n", product.getName(), totalNetValue);
        });

    char startLetter = 'J';
    System.out.println("\nUsuwanie produktów zaczynających się na literę '" + startLetter + "':");
    shop.doOnProductList(
        product -> {
          if (product.getName().toLowerCase().charAt(0) == Character.toLowerCase(startLetter)) {
            product.setQuantity(0);
            System.out.println("Usunięto produkt: " + product.getName());
          }
        });

    System.out.println("\nPełna informacja o produktach:");
    shop.doOnProductList(System.out::println);
  }
}
