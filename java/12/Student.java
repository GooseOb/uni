import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Student implements Runnable {
  private final int id;
  private final AtomicInteger correctAnswers = new AtomicInteger(0);
  private final Random random = new Random();

  public Student(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getCorrectAnswers() {
    return correctAnswers.get();
  }

  @Override
  public void run() {
    if (random.nextBoolean()) {
      correctAnswers.incrementAndGet();
    }
  }
}
