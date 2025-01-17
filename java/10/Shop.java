import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Shop {
  private final List<Product> products;

  public Shop() {
    this.products = new ArrayList<>();
  }

  public void addProduct(Product product) {
    products.add(product);
  }

  public void doOnProductList(Consumer<Product> action) {
    for (Product product : products) {
      action.accept(product);
    }
  }

  public List<Product> getProducts() {
    return products;
  }

  public double calculateTotalValue() {
    return this.products.stream()
        .mapToDouble(product -> product.getPrice() * product.getQuantity())
        .sum();
  }

  public List<String> createProductDescriptions() {
    return this.products.stream()
        .map(product -> product.getType().getNameInPolish() + " " + product.getName())
        .toList();
  }

  public void reduceFruitPrices() {
    this.products.stream()
        .filter(product -> product.getType() == ProductType.FRUIT)
        .forEach(product -> product.setPrice(product.getPrice() * 0.95));
  }

  public List<Product> getAndSortProductsUnderPrice(double priceThreshold) {
    return this.products.stream()
        .filter(product -> product.getPrice() < priceThreshold)
        .sorted(Comparator.comparing(Product::getName))
        .toList();
  }

  public Map<String, Integer> mapProductQuantities() {
    return this.products.stream().collect(Collectors.toMap(Product::getName, Product::getQuantity));
  }

  public boolean checkVegetableEndsWithA() {
    return this.products.stream()
        .filter(product -> product.getType() == ProductType.VEGETABLE)
        .anyMatch(product -> product.getName().endsWith("a"));
  }
}
