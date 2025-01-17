import java.time.LocalDate;

class PaperBook extends Book implements Rentable {
  int pages;

  public PaperBook(String title, String publisher, int resourceId, String author, int pages) {
    super(title, publisher, resourceId, author);
    this.pages = pages;
  }

  @Override
  public void print() {
    System.out.println("PaperBook: " + title + " by " + author);
  }

  @Override
  public void rent(User user) throws NoAvailableResourceException {
    if (this.user != null) {
      throw new NoAvailableResourceException("This resource is already rented.");
    }
    this.user = user;
    this.rentDate = LocalDate.now();
    this.dueDate =
        (user instanceof Student) ? LocalDate.now().plusMonths(1) : LocalDate.now().plusMonths(3);
  }

  @Override
  public User getUser() {
    return user;
  }
}
