import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {
  Utils() {
    throw new IllegalStateException("Utility class");
  }

  public static double calculateSpecialSum() {
    return IntStream.rangeClosed(1, 100).mapToDouble(i -> (i + 1) / (double) (i * i)).sum();
  }

  public static double randomNumbersAverage() {
    return new Random()
        .ints(15, 1, 101)
        .sorted()
        .limit(5)
        .peek(System.out::println)
        .average()
        .orElse(0.0);
  }

  public static double calculateAverageOfRandomDoubles() {
    return IntStream.range(0, 100)
        .mapToObj(i -> new Random().doubles(10, 0, 1).boxed().collect(Collectors.toList()))
        .flatMap(List::stream)
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0);
  }

  public static String generateRandomLetters() {
    return new Random()
        .ints(10, 'a', 'z' + 1)
        .mapToObj(i -> String.valueOf((char) i))
        .collect(Collectors.joining());
  }
}
