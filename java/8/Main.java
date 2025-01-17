import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        School school = School.getInstance("High School");

        Pupil pupil1 = new Pupil("Anna", "Kowalska", LocalDate.of(2005, 3, 15));
        Pupil pupil2 = new Pupil("Jan", "Nowak", LocalDate.of(2006, 7, 20));

        school.addPupil(pupil1);
        school.addPupil(pupil2);

        System.out.println(school);

        String codedName = Coder.code(pupil1.getName());
        String codedSurname = Coder.code(pupil1.getSurname());

        System.out.println("Zakodowane imię i nazwisko: " + codedName + " " + codedSurname);
    }
}
