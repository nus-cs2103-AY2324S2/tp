package seedu.teachstack.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.teachstack.commons.util.AppUtil.checkArgument;


/**
 * Represents a Student's grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGrade(String)}
 */

public class Grade implements Comparable<Grade> {

    private static Grade thresholdGrade = new Grade("C+");
    private static final String VALID_GRADES = "[A+, A, A-, B+, B, B-, C+, C, D+, D, F]";
    private static final String VALIDATION_REGEX = "A[+-]?|B[+-]?|C[+]?|D[+]?|F";

    //some regex here
    public static final String MESSAGE_CONSTRAINTS = "Grades should adhere to the following constraints:\n"
            + "1. The grade should be one of the valid grades:\n"
            + VALID_GRADES;

    public final String value;



    /**
     * Constructs an {@code Grade}.
     *
     * @param grade A valid grade.
     */
    public Grade(String grade) {
        requireNonNull(grade);
        //TODO: write some tests for isValidGrade
        checkArgument(isValidGrade(grade), MESSAGE_CONSTRAINTS);
        value = grade;
    }

    /**
     * Returns if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    private int gradeToInt() {
        switch (value) {
        case ("A+"):
            return 11;
        case ("A"):
            return 10;
        case ("A-"):
            return 9;
        case ("B+"):
            return 8;
        case ("B"):
            return 7;
        case ("B-"):
            return 6;
        case ("C+"):
            return 5;
        case ("C"):
            return 4;
        case ("D+"):
            return 3;
        case ("D"):
            return 2;
        case ("F"):
            return 1;
        default:
            return -1;
        }
    }

    @Override
    public String toString() {
        return value;
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
        return value.equals(otherGrade.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Grade o) {
        return this.gradeToInt() - o.gradeToInt();
    }

    /**
     * Returns whether grade constitutes weak or not
     */
    public boolean isWeak() {
        if (this.compareTo(thresholdGrade) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void modifyThreshold(Grade g) {
        thresholdGrade = g;
    }

    public String retrieveThreshold() {
        return thresholdGrade.value;
    }

}
