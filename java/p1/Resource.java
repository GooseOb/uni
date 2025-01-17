import java.time.LocalDate;

abstract class Resource {
  String title;
  String publisher;
  int resourceId;
  LocalDate rentDate;
  LocalDate dueDate;
  User user;

  public Resource(String title, String publisher, int resourceId) {
    this.title = title;
    this.publisher = publisher;
    this.resourceId = resourceId;
  }

  public abstract void print(); // To be implemented in subclasses

  public Status getStatus() {
    if (user == null) {
      return Status.AVAILABLE;
    } else {
      return Status.UNAVAILABLE;
    }
  }

  public User getUser() {
    return user;
  }
}
