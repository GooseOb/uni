import java.time.LocalDate;
import java.util.Objects;

public final class Pupil {
    private final String name;
    private final String surname;
    private final LocalDate birthday;

    public Pupil(String name, String surname, LocalDate birthday) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    @Override
    public Pupil clone() {
        return new Pupil(this.name, this.surname, this.birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pupil pupil = (Pupil) o;
        return name.equals(pupil.name) &&
                surname.equals(pupil.surname) &&
                birthday.equals(pupil.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthday);
    }

    @Override
    public String toString() {
        return "Pupil{name='" + name + "', surname='" + surname + "', birthday=" + birthday + "}";
    }
}
