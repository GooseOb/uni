import java.util.List;
import java.util.Random;

class ComputerPlayer extends Player {
  public ComputerPlayer(
      String firstName, String lastName, int birthYear, int totalMoney, int boxesOpened) {
    super(firstName, lastName, birthYear, totalMoney, boxesOpened);
  }

  public int chooseBox(List<Integer> boxes) {
    return new Random().nextInt(boxes.size());
  }

  @Override
  public ComputerPlayer clone() {
    return new ComputerPlayer(firstName, lastName, birthYear, totalMoney, boxesOpened);
  }
}
