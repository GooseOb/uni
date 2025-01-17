import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Properties config = new Properties();

    try (FileInputStream input = new FileInputStream("resources//config.properties")) {
      config.load(input);
    } catch (IOException e) {
      System.out.println("Error loading config file: " + e.getMessage());
      return;
    }

    String operation = config.getProperty("operation", "perimeter");
    String language = config.getProperty("language", "en");

    Locale locale =
        switch (language.toLowerCase()) {
          case "pl" -> Locale.of("pl", "PL");
          case "de" -> Locale.of("de", "DE");
          default -> Locale.of("en", "US");
        };

    ResourceBundle messages = ResourceBundle.getBundle("messages", locale);
    Scanner scanner = new Scanner(System.in);

    System.out.println(messages.getString("enter_side_a"));
    double a = scanner.nextDouble();

    System.out.println(messages.getString("enter_side_b"));
    double b = scanner.nextDouble();

    System.out.println(messages.getString("enter_side_c"));
    double c = scanner.nextDouble();

    scanner.close();

    if (!isTriangleValid(a, b, c)) {
      System.out.println(messages.getString("invalid_triangle"));
      return;
    }

    if (operation.equalsIgnoreCase("perimeter")) {
      double perimeter = calculatePerimeter(a, b, c);
      System.out.println(messages.getString("perimeter") + ": " + perimeter);
    } else if (operation.equalsIgnoreCase("area")) {
      double area = calculateArea(a, b, c);
      System.out.println(messages.getString("area") + ": " + area);
    } else {
      System.out.println(messages.getString("invalid_operation"));
    }
  }

  private static boolean isTriangleValid(double a, double b, double c) {
    return a + b > c && a + c > b && b + c > a;
  }

  private static double calculatePerimeter(double a, double b, double c) {
    return a + b + c;
  }

  private static double calculateArea(double a, double b, double c) {
    double s = (a + b + c) / 2;
    return Math.sqrt(s * (s - a) * (s - b) * (s - c));
  }
}
