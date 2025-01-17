import java.util.*;

class HumanPlayer extends Player {
  public HumanPlayer(
      String firstName, String lastName, int birthYear, int totalMoney, int boxesOpened) {
    super(firstName, lastName, birthYear, totalMoney, boxesOpened);
  }

  public int chooseBox(List<Integer> boxes) {
    System.out.println(getName() + ", dostępne pudełka: " + boxes);
    System.out.print("Wybierz numer pudełka (0 - " + (boxes.size() - 1) + "): ");
    Scanner scanner = new Scanner(System.in);
    int value = scanner.nextInt();
    scanner.close();
    return value;
  }

  @Override
  public HumanPlayer clone() {
    return new HumanPlayer(firstName, lastName, birthYear, totalMoney, boxesOpened);
  }
}
