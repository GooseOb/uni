import java.util.*;
import java.util.stream.Collectors;

class Game implements Comparable<Game> {
  private final List<Player> players;
  private List<Player> playersDiff;
  private Player winner;
  private int totalPrize;

  public int getTotalPrize() {
    return totalPrize;
  }

  public void setTotalPrize(int totalPrize) {
    this.totalPrize = totalPrize;
  }

  private final List<Integer> boxes;

  public Game(List<Player> players, List<Integer> boxes) {
    this.players = players;
    this.boxes = boxes;
  }

  public void play() {
    playersDiff = players.stream().map(Player::clone).collect(Collectors.toList());
    playersDiff.forEach(Player::reset);

    playing:
    while (true) {
      for (Player player : playersDiff) {
        player.openBox(boxes.remove(player.chooseBox(boxes)));
        if (boxes.isEmpty()) break playing;
      }
    }

    winner = findWinner();
    winner.addWin();

    for (int i = 0; i < players.size(); i++) {
      players.get(i).merge(playersDiff.get(i));
    }

    totalPrize = playersDiff.stream().mapToInt(Player::getTotalMoney).sum();

    displayResults();
  }

  private Player findWinner() {
    return playersDiff.stream().max(Comparator.comparingInt(Player::getTotalMoney)).orElse(null);
  }

  private void displayResults() {
    if (winner != null) {
      System.out.println(
          "Zwycięzca: "
              + winner.getName()
              + ", Wygrana: "
              + winner.getTotalMoney()
              + ", Pudełka otwarte: "
              + winner.getBoxesOpened());
    }

    List<Player> playersDiffSorted =
        playersDiff.stream()
            .sorted(Comparator.comparingInt(Player::getTotalMoney).reversed())
            .toList();

    playersDiffSorted.forEach(System.out::println);

    int playersNum = playersDiffSorted.size();

    if (playersNum > 1) {
      System.out.println(
          "Najbardziej wartościowe pudełko otworzył: " + playersDiffSorted.get(0).getName());

      System.out.println(
          "Najmniej wartościowe pudełko otworzył: "
              + playersDiffSorted.get(playersNum - 1).getName());
    }

    System.out.println("Łączna pula nagród: " + totalPrize);
  }

  @Override
  public int compareTo(Game o) {
    return Integer.compare(totalPrize, o.totalPrize);
  }
}
