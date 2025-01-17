import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Person {
  private String name;
  private String surname;
  private LocalDate birthDate;
  private LocalTime timeOfBirth;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = parseName("Imie", name);
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = parseName("Nazwisko", surname);
  }

  private String parseName(String what, String input) {
    if (input.matches("[A-Z][a-z]+")) {
      return input;
    } else {
      throw new IllegalArgumentException(
          what + " musi zaczynać się wielką literą i składać się tylko z liter.");
    }
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    if (birthDate.isBefore(LocalDate.now())) {
      this.birthDate = birthDate;
    } else {
      throw new IllegalArgumentException("Data urodzenia musi być z przeszłości.");
    }
  }

  public LocalTime getTimeOfBirth() {
    return timeOfBirth;
  }

  public void setTimeOfBirth(LocalTime timeOfBirth) {
    this.timeOfBirth = timeOfBirth;
  }

  public String getGender() {
    return name.endsWith("a") ? "Kobieta" : "Mężczyzna";
  }

  public int getAge() {
    return Period.between(birthDate, LocalDate.now()).getYears();
  }

  public String getDayOfBirth() {
    DayOfWeek dayOfWeek = birthDate.getDayOfWeek();
    return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
  }

  public String reverseNameAndSurname() {
    return initCap(reverse(name)) + " " + initCap(reverse(surname));
  }

  public String getBirthDateTimeInLosAngeles() {
    ZonedDateTime birthDateTimePoland =
        ZonedDateTime.of(birthDate, timeOfBirth, ZoneId.of("Europe/Warsaw"));
    ZonedDateTime birthDateTimeLA =
        birthDateTimePoland.withZoneSameInstant(ZoneId.of("America/Los_Angeles"));
    return birthDateTimeLA.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
  }

  private String reverse(String input) {
    return new StringBuilder(input).reverse().toString();
  }

  private String initCap(String input) {
    return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
  }
}
