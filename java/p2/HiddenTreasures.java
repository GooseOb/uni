import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HiddenTreasures {
  public static void main(String[] args) {
    Simulator simulator = Simulator.getInstance();
    try {
      simulator.simulateGames(loadPlayers("players.txt"), "raport.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static List<Player> loadPlayers(String filePath) throws IOException {
    return Files.lines(Paths.get(filePath))
        .map(
            (line) -> {
              String[] parts = line.split(";");
              return new ComputerPlayer(
                  parts[0],
                  parts[1],
                  Integer.parseInt(parts[2]),
                  Integer.parseInt(parts[4]),
                  Integer.parseInt(parts[3]));
            })
        .collect(Collectors.toList());
  }
}
