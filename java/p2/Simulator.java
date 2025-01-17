import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class Simulator {
  private static Simulator instance;

  private Simulator() {}

  public static Simulator getInstance() {
    if (instance == null) {
      instance = new Simulator();
    }
    return instance;
  }

  public void simulateGames(List<Player> players, String reportFile) throws IOException {
    Random random = new Random();
    List<Game> games = new ArrayList<>();

    for (int i = 0; i < 100; i++) {
      List<Integer> boxes = random.ints(700, 10, 100).boxed().collect(Collectors.toList());
      Game game = new Game(players, boxes);
      game.play();
      games.add(game);
    }

    System.out.println("Puli nagród gier:");
    games.stream()
        .mapToInt(Game::getTotalPrize)
        .boxed()
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);

    int totalWinnerWinnings =
        players.stream().filter(player -> player.wins > 0).mapToInt(Player::getTotalMoney).sum();

    int maxWins = players.stream().mapToInt(Player::getWins).max().orElse(0);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
      writer.write("Gracze z największą liczbą zwycięstw (" + maxWins + "):\n");
      players.stream()
          .filter(player -> player.getWins() == maxWins)
          .forEach(
              winner -> {
                try {
                  writer.write(winner + "\n");
                } catch (IOException e) {
                  e.printStackTrace();
                }
              });
      writer.write("\nŁączna wygrana wszystkich zwycięzców: " + totalWinnerWinnings + "\n");
    }
  }
}
