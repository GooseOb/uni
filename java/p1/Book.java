abstract class Book extends Resource {
  String author;

  public Book(String title, String publisher, int resourceId, String author) {
    super(title, publisher, resourceId);
    this.author = author;
  }

  public boolean isTheSameBook(Book book1) {
    return this.title.equals(book1.title) && this.author.equals(book1.author);
  }
}
