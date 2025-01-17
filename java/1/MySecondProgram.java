class MySecondProgram {
  public static void main(String[] args) {
    Book book1 = new Book("The Lord of the Rings", 1000);
    Book book2 = new Book("The Hobbit", 300);

    System.out.println(book1.title);
    System.out.println("Pages: " + book1.pages);
    System.out.println("Read pages: " + book1.readPagesNum);
    System.out.println("Is read: " + book1.isRead + "\n");

    System.out.println(book2.title);
    System.out.println("Pages: " + book2.pages);
    System.out.println("Read pages: " + book2.readPagesNum);
    System.out.println("Is read: " + book2.isRead + "\n");

    book1.addReadPages(1000);
    book2.addReadPages(100);

    System.out.println(book1.title);
    System.out.println("Read pages: " + book1.readPagesNum);
    System.out.println("Is read: " + book1.isRead + "\n");

    System.out.println(book2.title);
    System.out.println("Read pages: " + book2.readPagesNum);
    System.out.println("Is read: " + book2.isRead + "\n");
  }
}
