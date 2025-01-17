import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class School {
    private static School instance;
    private String name;
    private final List<Pupil> pupilsList;

    private School(String name) {
        this.name = name;
        this.pupilsList = new ArrayList<>();
    }

    public static School getInstance(String name) {
        if (instance == null) {
            instance = new School(name);
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pupil> getPupilsList() {
        return Collections.unmodifiableList(pupilsList);
    }

    public void addPupil(Pupil pupil) {
        this.pupilsList.add(pupil);
    }

    @Override
    public String toString() {
        return "School{name='" + name + "', pupilsList=" + pupilsList + "}";
    }
}
