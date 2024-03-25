package seedu.edulink.model;

import seedu.edulink.model.student.Student;

public class ModificationHistory {

    private final String type;
    private final Student firstAffectedStudent;
    private Student secondAffectedStudent;
    public ModificationHistory(String type, Student firstAffectedStudent) {
        this.type = type;
        this.firstAffectedStudent = firstAffectedStudent;
    }

    public ModificationHistory(String type, Student firstAffectStudent, Student secondAffectedStudent) {
        this(type, firstAffectStudent);
        this.secondAffectedStudent = secondAffectedStudent;
    }

    public Student getFirstAffectedStudent() {
        return this.firstAffectedStudent;
    }

    public Student getSecondAffectStudent() {
        return this.secondAffectedStudent;
    }

    public String getType() {
        return this.type;
    }
}
