public class Product {
  private ProductType type;
  private String name;
  private double price;
  private int quantity;

  public Product(ProductType type, String name, double price, int quantity) {
    this.type = type;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public ProductType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getNetPrice() {
    return price / (1 + type.getVat());
  }

  @Override
  public String toString() {
    return String.format(
        "%s, %s, Cena netto %.2f zł, Cena brutto %.2f zł, w sklepie sztuk: %d.",
        name, type.getNameInPolish(), getNetPrice(), price, quantity);
  }
}
