import java.util.Scanner;

public class myThirdProgram {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Podaj nazwisko studenta:");
    String lastname = scanner.nextLine();

    System.out.println("Podaj liczbę przedmiotów:");
    int numOfCourses = scanner.nextInt();

    StudentIndex student1 = new StudentIndex(lastname, numOfCourses);

    student1.readGrades(scanner);
    scanner.close();

    student1.printLastnameWithGrades();

    var maxGrade = student1.getMaxGrade();
    System.out.println(
        "Najwyższa ocena: " + maxGrade + " (" + StudentIndex.gradeToString(maxGrade) + ")");
    student1.printLackingGrades();
  }
}
