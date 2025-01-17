package homeworks;

import java.util.Arrays;
import java.util.Scanner;

public class NewStudentIndex {

    public final String TWO_STRING = "Niedostateczny";
    public final String THREE_STRING = "Dostateczny";
    public final String THREE_AND_HALF_STRING = "Dostateczny+";
    public final String FOUR_STRING = "Dobry";
    public final String FOUR_AND_HALF_STRING = "Dobry+";
    public final String FIVE_STRING = "Bardzo dobry";

    class WrongDataFormatException extends Exception {
        private static final long serialVersionUID = 1L;
    }
    public String surname;
    public int numberOfSubjects;
    public double[] grades;

    public NewStudentIndex(String surname,int numberOfSubjects) {
        this.surname = surname;
        this.numberOfSubjects = numberOfSubjects;
        grades = new double[numberOfSubjects];
    }

    public void getGrades()
    {
        for(int i=0;i<grades.length;i++) {
            System.out.println("Grade nr."+(i+1)+": ");
            Scanner scan = new Scanner(System.in);
            grades[i] = scan.nextDouble();
        }
    }

    public String printGrades() {
        StringBuilder result = new StringBuilder();
        result.append("Student: "+surname);
        for(int i=0;i<grades.length;i++) {
            String grade = new String();
            switch((int)(grades[i]*10)) {
                case 20:
                    grade = TWO_STRING;
                    break;
                case 30:
                    grade = THREE_STRING;
                    break;
                case 35:
                    grade = THREE_AND_HALF_STRING;
                    break;
                case 40:
                    grade = FOUR_STRING;
                    break;
                case 45:
                    grade = FOUR_AND_HALF_STRING;
                    break;
                case 50:
                    grade = FIVE_STRING;
                    break;
            }
            result.append(grade);
            if(i!=grades.length-1)
                result.append(";");
        }
        return result.toString();
    }

    public double getMaxGrade() {
        Arrays.parallelSort(grades);
        return grades[grades.length-1];
    }

    public double getAverage() {
        double sum = 0.0;
        for(int i=0;i<grades.length;i++)
            sum+=grades[i];
        return sum/grades.length;
    }

    public String printMissingGrades() {
        StringBuilder result = new StringBuilder();
        double[] avaiableGrades = {2,3,3.5,4,4.5,5};
        iLoop:
        for(int i=0;i<avaiableGrades.length;i++) {
            for(int j=0;j<grades.length;j++) {
                if(grades[j]==avaiableGrades[i])
                    continue iLoop;
            }
            result.append("Missing grade: "+avaiableGrades[i]);
        }
        return result.toString();
    }

    public void addGrades(double... grades) throws WrongDataFormatException {
        if (grades.length != this.grades.length)
            throw new WrongDataFormatException();
        for (int i=0;i<grades.length;i++) {
            if(isCorrectForm(grades[i]))
                this.grades[i] = grades[i];
            else
                throw new WrongDataFormatException();
        }
    }

    static boolean isCorrectForm(double grade) {

        double hundredthPart = grade - (int)(grade*10)/10.0;
        if (hundredthPart>0)
            return false;
        if (grade == 2.5)
            return false;
        if (grade<2 || grade>5)
            return false;
        return true;
    }
}
