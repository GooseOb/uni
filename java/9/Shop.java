import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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
}
