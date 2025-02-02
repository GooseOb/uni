public enum ProductType {
  FRUIT("Owoc", 0.08),
  VEGETABLE("Warzywo", 0.08),
  OTHER("Inne", 0.23);

  private final String nameInPolish;
  private final double vat;

  ProductType(String nameInPolish, double vat) {
    this.nameInPolish = nameInPolish;
    this.vat = vat;
  }

  public String getNameInPolish() {
    return nameInPolish;
  }

  public double getVat() {
    return vat;
  }
}
