package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

public class IdMatchesPredicate implements Predicate<Person> {
    private final StudentId studentId;

    public IdMatchesPredicate(StudentId studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean test(Person person) {
        return person.getStudentId().equals(studentId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IdMatchesPredicate)) {
            return false;
        }

        IdMatchesPredicate otherId = (IdMatchesPredicate) other;
        return studentId.equals(otherId.studentId);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("student id", studentId).toString();
    }
}

