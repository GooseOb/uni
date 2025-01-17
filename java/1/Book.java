class Book {
  public final String title;
  public final int pages;

  public int readPagesNum = 0;
  public boolean isRead = false;

  public Book(String title, int pages) {
    this.title = title;
    this.pages = pages;
  }

  public void addReadPages(int value) {
    readPagesNum += value;
    isRead = readPagesNum >= pages;
  }
}
