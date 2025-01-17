class Teacher extends User {
  String position;

  public Teacher(String name, String surname, int idUser, String department, String position) {
    super(name, surname, idUser, department);
    this.position = position;
  }
}
