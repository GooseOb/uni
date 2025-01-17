import java.time.LocalDate;

abstract class User {
  String name;
  String surname;
  int idUser;
  String department;
  int resourcesCount;

  public User(String name, String surname, int idUser, String department) {
    this.name = name;
    this.surname = surname;
    this.idUser = idUser;
    this.department = department;
    this.resourcesCount = 0;
  }

  public int numberOfResources(Resource[] resources) {
    int count = 0;
    for (Resource resource : resources) {
      if (resource.getUser() != null && resource.getUser().idUser == this.idUser) {
        count++;
      }
    }
    return count;
  }

  public double getFee(Resource[] resources) {
    double fee = 0;
    for (Resource resource : resources) {
      if (resource.dueDate != null
          && resource.getUser() != null
          && resource.getUser().idUser == this.idUser
          && resource.dueDate.isBefore(LocalDate.now())) {
        System.out.println("resource.dueDate: ");
        long overdueDays = LocalDate.now().toEpochDay() - resource.dueDate.toEpochDay();
        fee += overdueDays * 1;
      }
    }
    return fee;
  }
}
