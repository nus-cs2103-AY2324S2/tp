package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSTHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MORETHAN;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.exam.Exam;

/**
 * Tests that a {@code Person}'s details contains the keyword given.
 */
public class PersonDetailContainsKeywordAndExamPredicate implements Predicate<Person> {
    private final Prefix prefix;
    private final String keyword;
    private final Exam exam;

    /**
     * Constructor for the PersonDetailContainsKeywordPredicate.
     * @param prefix The prefix to indicate the detail of the person to be searched.
     * @param keyword The keyword to be searched.
     */
    public PersonDetailContainsKeywordAndExamPredicate(Prefix prefix, String keyword, Exam exam) {
        this.prefix = prefix;
        this.keyword = keyword;
        this.exam = exam;
    }

    /**
     * Tests if the person's details contain the keyword.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_LESSTHAN.equals(prefix)) {
            if (person.getScores().containsKey(exam)) {
                return Double.parseDouble(person.getScores().get(exam).toString())
                        < Double.parseDouble(keyword);
            } else {
                // Handle case when the selected exam is not found in the person's scores
                return false;
            }
        } else if (PREFIX_MORETHAN.equals(prefix)) {
            if (person.getScores().containsKey(exam)) {
                return Double.parseDouble(person.getScores().get(exam).toString())
                        > Double.parseDouble(keyword);
            } else {
                // Handle case when the selected exam is not found in the person's scores
                return false;
            }
        } else {
            // Code should not reach here
            return false;
        }
    }

    /**
     * Checks if the current PersonDetailContainsKeywordPredicate is equal to another object.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonDetailContainsKeywordAndExamPredicate)) {
            return false;
        }

        PersonDetailContainsKeywordAndExamPredicate otherPredicate =
            (PersonDetailContainsKeywordAndExamPredicate) other;

        return keyword.equals(otherPredicate.keyword) && prefix.equals(otherPredicate.prefix)
                && exam.equals(otherPredicate.exam);
    }

    /**
     * Returns the string representation of the PersonDetailContainsKeywordPredicate.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("prefix", prefix.getPrefix())
                .add("keyword", keyword)
                .add("exam", exam).toString();
    }

    /**
     * Checks if the prefix requires an exam to be selected.
     * @return True if the prefix is PREFIX_LESSTHAN or PREFIX_GREATERTHAN.
     */
    public boolean isExamRequired() {
        return prefix.equals(PREFIX_LESSTHAN) || prefix.equals(PREFIX_MORETHAN);
    }
}

