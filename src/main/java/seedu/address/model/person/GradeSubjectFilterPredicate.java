package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Student}'s {@code Grade} or {@code Subject} matches any of the keywords given.
 */
public class GradeSubjectFilterPredicate implements Predicate<Person> {

    private final Grade filteredGrade;
    private final Subject filteredSubject;

    /**
     * Predicate that filters the address book by Grade or Subject.
     *
     * @param grade Grade to filter by.
     * @param subject Subject to filter by.
     */
    public GradeSubjectFilterPredicate(Grade grade, Subject subject) {
        this.filteredGrade = grade;
        this.filteredSubject = subject;
    }

    @Override
    public boolean test(Person person) {
        boolean isGradeFiltered = true;
        boolean isSubjectFiltered = true;
        if (!filteredGrade.equals(new Grade())) {
            isGradeFiltered = person.getGrade().equals(filteredGrade);
        }
        if (!filteredSubject.equals(new Subject())) {
            isSubjectFiltered = person.getSubject().equals(filteredSubject);
        }
        return isGradeFiltered && isSubjectFiltered;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        GradeSubjectFilterPredicate otherGradeSubjectFilterPredicate = (GradeSubjectFilterPredicate) other;
        return otherGradeSubjectFilterPredicate.equals(this);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("grade", filteredGrade)
                .add("subject", filteredSubject)
                .toString();
    }
}
