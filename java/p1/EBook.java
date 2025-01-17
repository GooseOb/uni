class EBook extends Book implements Digital {
  double size;
  int numberOfDownloads;

  public EBook(String title, String publisher, int resourceId, String author, double size) {
    super(title, publisher, resourceId, author);
    this.size = size;
    this.numberOfDownloads = 0;
  }

  @Override
  public Status getStatus() {
    return Status.DIGITAL;
  }

  @Override
  public void print() {
    System.out.println("EBook: " + title + ", Size: " + size + "MB");
  }

  @Override
  public void download() {
    numberOfDownloads++;
  }
}
