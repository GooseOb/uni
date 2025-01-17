import java.util.Scanner;

public class Program {
  static final double MIN = 0.1;
  static final double MAX = 9999.99;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a number in the range " + MIN + "-" + MAX + ": ");

    try {
      double number = Double.parseDouble(scanner.nextLine());
      if (number < 0) {
        throw new IllegalArgumentException("Number must be positive");
      } else if (number < MIN || number > MAX) {
        throw new IllegalArgumentException("Number is out of range.");
      }

      double result = Math.sqrt(number);
      System.out.printf("Square root of " + number + " is " + result);
    } catch (NumberFormatException e) {
      System.out.println("Not a number.");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    } catch (Exception e) {
      System.out.println("An unexpected error occurred: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
