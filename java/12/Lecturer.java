import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Lecturer implements Runnable {
  private final List<Student> students;

  public Lecturer(List<Student> students) {
    this.students = students;
  }

  @Override
  public void run() {
    for (int round = 1; round <= 5; round++) {
      ExecutorService executor = Executors.newFixedThreadPool(students.size());
      for (int question = 1; question <= 20; question++) {
        for (Student student : students) {
          executor.execute(student);
        }
      }
      executor.shutdown();
      while (!executor.isTerminated())
        ;
    }

    students.sort((s1, s2) -> Integer.compare(s2.getCorrectAnswers(), s1.getCorrectAnswers()));

    System.out.println("Results:");
    for (Student student : students) {
      System.out.printf(
          "Student %d: %d correct answers\n", student.getId(), student.getCorrectAnswers());
    }
  }
}
