import java.util.List;
import java.util.Objects;

abstract class Player implements Cloneable {
  protected String firstName;
  protected String lastName;
  protected int birthYear;
  protected int totalMoney;
  protected int boxesOpened;
  protected int wins;

  public void addWin() {
    ++wins;
  }

  public int getWins() {
    return wins;
  }

  public Player(String firstName, String lastName, int birthYear, int totalMoney, int boxesOpened) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthYear = birthYear;
    this.totalMoney = totalMoney;
    this.boxesOpened = boxesOpened;
    this.wins = 0;
  }

  public void reset() {
    totalMoney = 0;
    boxesOpened = 0;
    wins = 0;
  }

  public void merge(Player player) {
    totalMoney += player.totalMoney;
    boxesOpened += player.boxesOpened;
    wins += player.wins;
  }

  public void openBox(int money) {
    totalMoney += money;
    boxesOpened++;
  }

  public int getTotalMoney() {
    return totalMoney;
  }

  public int getBoxesOpened() {
    return boxesOpened;
  }

  public String getName() {
    return firstName + " " + lastName;
  }

  public abstract int chooseBox(List<Integer> boxes);

  @Override
  public abstract Player clone();

  @Override
  public String toString() {
    return getName()
        + " (Łącznie pieniędzy: "
        + totalMoney
        + ", Pudełka otwarte: "
        + boxesOpened
        + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Player player = (Player) obj;
    return birthYear == player.birthYear
        && Objects.equals(firstName, player.firstName)
        && Objects.equals(lastName, player.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, birthYear);
  }
}
