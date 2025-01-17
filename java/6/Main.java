import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Person person = new Person();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    try {
      System.out.print("Podaj imię: ");
      String name = scanner.nextLine();
      person.setName(name);

      System.out.print("Podaj nazwisko: ");
      String surname = scanner.nextLine();
      person.setSurname(surname);

      System.out.print("Podaj datę urodzenia (dd.MM.yyyy): ");
      String birthDateInput = scanner.nextLine();
      LocalDate birthDate = LocalDate.parse(birthDateInput, dateFormatter);
      person.setBirthDate(birthDate);

      System.out.print("Podaj godzinę urodzenia (HH:mm): ");
      String timeOfBirthInput = scanner.nextLine();
      LocalTime timeOfBirth = LocalTime.parse(timeOfBirthInput);
      person.setTimeOfBirth(timeOfBirth);

      System.out.println("Płeć: " + person.getGender());
      System.out.println("Wiek: " + person.getAge());
      System.out.println("Dzień tygodnia urodzenia: " + person.getDayOfBirth());
      System.out.println("Odwrócone imię i nazwisko: " + person.reverseNameAndSurname());
      System.out.println(
          "Data i godzina urodzenia w Los Angeles: " + person.getBirthDateTimeInLosAngeles());

    } catch (Exception e) {
      System.out.println("Wystąpił błąd: " + e.getMessage());
    }

    scanner.close();
  }
}
