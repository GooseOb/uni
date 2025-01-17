import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Student> students = new ArrayList<>();
    for (int i = 1; i <= 20; i++) {
      students.add(new Student(i));
    }

    Lecturer lecturer = new Lecturer(students);

    Thread lecturerThread = new Thread(lecturer);
    lecturerThread.start();

    try {
      lecturerThread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
