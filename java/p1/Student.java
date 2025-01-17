class Student extends User {
  String course;

  public Student(String name, String surname, int idUser, String department, String course) {
    super(name, surname, idUser, department);
    this.course = course;
  }
}
