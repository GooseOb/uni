package homeworks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NewStudentIndexTest {
    NewStudentIndex student;

    @BeforeEach
    void setUp() {
        student = new NewStudentIndex("Imie", 3);
    }

    @Test
    void addGradesValid() throws NewStudentIndex.WrongDataFormatException {
        student.addGrades(2, 3, 4);
        assertArrayEquals(new double[]{2, 3, 4}, student.grades);
    }

    @Test
    void addGradesInvalidFormat() {
        assertThrows(NewStudentIndex.WrongDataFormatException.class, () -> {
            student.addGrades(2.5, 3, 5);
        });
        assertThrows(NewStudentIndex.WrongDataFormatException.class, () -> {
            student.addGrades(2, 3, 5, 3);
        });
        assertThrows(NewStudentIndex.WrongDataFormatException.class, () -> {
            student.addGrades(14, 3, 5);
        });
    }

    @Test
    void printGrades() throws NewStudentIndex.WrongDataFormatException {
        student.addGrades(2, 3.5, 5);
        assertEquals("Student: ImieNiedostateczny;Dostateczny+;Bardzo dobry", student.printGrades());
    }

    @Test
    void getMaxGrade() throws NewStudentIndex.WrongDataFormatException {
        student.addGrades(2, 3.5, 5);
        assertEquals(5, student.getMaxGrade());
    }

    @Test
    void getAverage() throws NewStudentIndex.WrongDataFormatException {
        student.addGrades(2, 4, 4.5);
        assertEquals(3.5, student.getAverage(), 0.001);
    }

    @Test
    void printMissingGrades() throws NewStudentIndex.WrongDataFormatException {
        student.addGrades(2, 4, 5);
        assertTrue(student.printMissingGrades().contains("Missing grade: 3"));
        assertTrue(student.printMissingGrades().contains("Missing grade: 3.5"));
    }
}
