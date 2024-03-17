package seedu.address.model.grade;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.stream.Stream;

/**
 * Represents a Student's Grade in the Application.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */
public class Grade {
    public static final String MESSAGE_CONSTRAINTS =
            "Grade should only contain single Alphabetic character [A,B,C,D,F]";
    public static final String VALIDATION_REGEX = "[a-zA-Z]";

    public final Grades grade;

    /**
     * Constructs a {@code Major}.
     *
     * @param grade A valid Grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        this.grade = parseGrade(grade);
    }
    /**
     * Validates a Grade
     * @param test an input to validate.
     */
    public static boolean isValidGrade(String test) {
        boolean isValid;
        Stream<String> valid = Stream.of("A", "B", "C", "D", "F");
        isValid = valid.anyMatch((input) -> input.equalsIgnoreCase(test));
        return test.matches(VALIDATION_REGEX) && isValid;
    }

    /**
     * parses the grade into enum Grade
     * @param grade an input to parse.
     */
    public Grades parseGrade(String grade) {
        switch (grade.toUpperCase()) {
        case "A":
            return Grades.A;
        case "B":
            return Grades.B;
        case "C":
            return Grades.C;
        case "D":
            return Grades.D;
        case "F":
            return Grades.F;
        default:
            return Grades.N;
        }
    }

    @Override
    public String toString() {
        if (this.grade.equals(Grades.N)) {
            return "";
        }
        return this.grade.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return this.grade.equals(otherGrade.grade);
    }

    @Override
    public int hashCode() {
        return this.grade.hashCode();
    }
}
