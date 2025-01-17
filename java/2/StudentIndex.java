import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StudentIndex {
  public String lastname;
  public int numOfCourses;
  public int[] grades;

  public StudentIndex(String lastname, int numOfCourses) {
    this.lastname = lastname;
    this.numOfCourses = numOfCourses;
    grades = new int[numOfCourses];
  }

  public void printLastnameWithGrades() {
    System.out.println("Student " + lastname);
    System.out.println(
        "Oceny: "
            + Arrays.stream(grades)
                .mapToObj(StudentIndex::gradeToString)
                .collect(Collectors.joining(", ")));
  }

  public int getMaxGrade() {
    if (grades.length == 0) return 0;
    Arrays.sort(grades);
    return grades[grades.length - 1];
  }

  public void readGrades(Scanner scanner) {
    for (int i = 0; i < numOfCourses; i++) {
      System.out.print("Podaj ocenę dla przedmiotu " + i + " (2-5): ");
      while (true) {
        grades[i] = scanner.nextInt();
        if (grades[i] >= MIN_GRADE && grades[i] <= MAX_GRADE) {
          break;
        }
        System.out.println(
            "Nieprawidłowa ocena. Wprowadź ocenę od " + MIN_GRADE + " do " + MAX_GRADE + ".");
      }
    }
  }

  public void printLackingGrades() {
    System.out.println("Oceny, których student nie dostał:");
    outer:
    for (int i = 2; i <= 5; i++) {
      for (int grade : grades) {
        if (grade == i) continue outer;
      }
      System.out.println(gradeToString(i));
    }
  }

  public static int MIN_GRADE = 2;
  public static int MAX_GRADE = 5;

  public static String gradeToString(int grade) {
    return switch (grade) {
      case 5 -> "Bardzo dobry";
      case 4 -> "Dobry";
      case 3 -> "Dostateczny";
      case 2 -> "Niedostateczny";
      default -> "Nieznana ocena";
    };
  }
}
