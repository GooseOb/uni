import java.time.LocalDate;

class Journal extends Resource implements Rentable {
  LocalDate publishDate;

  public Journal(String title, String publisher, int resourceId, LocalDate publishDate) {
    super(title, publisher, resourceId);
    this.publishDate = publishDate;
  }

  @Override
  public void print() {
    System.out.println("Journal: " + title + ", Published on: " + publishDate);
  }

  @Override
  public void rent(User user) throws NoAvailableResourceException {
    if (this.user != null) {
      throw new NoAvailableResourceException("This resource is already rented.");
    }
    this.user = user;
    this.rentDate = LocalDate.now();
    this.dueDate = LocalDate.now().plusDays(10);
  }

  @Override
  public User getUser() {
    return user;
  }
}
