class Audiobook extends Book implements Digital {
  int length;
  int numberOfDownloads;

  public Audiobook(String title, String publisher, int resourceId, String author, int length) {
    super(title, publisher, resourceId, author);
    this.length = length;
    this.numberOfDownloads = 0;
  }

  @Override
  public Status getStatus() {
    return Status.DIGITAL;
  }

  @Override
  public void print() {
    System.out.println("Audiobook: " + title + " by " + author + ", Length: " + length);
  }

  @Override
  public void download() {
    numberOfDownloads++;
  }
}
