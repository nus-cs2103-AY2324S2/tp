package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSTHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MORETHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.exam.Exam;

/**
 * Tests that a {@code Person}'s details contains the keyword given.
 */
public class PersonDetailContainsKeywordPredicate implements Predicate<Person> {
    private final Prefix prefix;
    private final String keyword;

    /**
     * Constructor for the PersonDetailContainsKeywordPredicate.
     * @param prefix The prefix to indicate the detail of the person to be searched.
     * @param keyword The keyword to be searched.
     */
    public PersonDetailContainsKeywordPredicate(Prefix prefix, String keyword) {
        this.prefix = prefix;
        this.keyword = keyword;
    }

    /**
     * Tests if the person's details contain the keyword.
     */
    @Override
    public boolean test(Person person) {
        if (PREFIX_NAME.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);
        } else if (PREFIX_PHONE.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getPhone().value, keyword);
        } else if (PREFIX_EMAIL.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getEmail().value, keyword);
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getAddress().value, keyword);
        } else if (PREFIX_TAG.equals(prefix)) {
            return person.getTags().stream()
                         .anyMatch(tag -> StringUtil.containsSubstringIgnoreCase(tag.tagName, keyword));
        } else if (PREFIX_MATRIC_NUMBER.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getMatric().matricNumber, keyword);
        } else if (PREFIX_REFLECTION.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getReflection().reflection, keyword);
        } else if (PREFIX_STUDIO.equals(prefix)) {
            return StringUtil.containsSubstringIgnoreCase(person.getStudio().studio, keyword);
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
        if (!(other instanceof PersonDetailContainsKeywordPredicate)) {
            return false;
        }

        PersonDetailContainsKeywordPredicate otherPredicate =
            (PersonDetailContainsKeywordPredicate) other;

        return keyword.equals(otherPredicate.keyword) && prefix.equals(otherPredicate.prefix);
    }

    /**
     * Returns the string representation of the PersonDetailContainsKeywordPredicate.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("prefix", prefix.getPrefix())
                                        .add("keyword", keyword).toString();
    }

    /**
     * Checks if the prefix requires an exam to be selected.
     * @return True if the prefix is PREFIX_LESSTHAN or PREFIX_GREATERTHAN.
     */
    public boolean isExamRequired() {
        return prefix.equals(PREFIX_LESSTHAN) || prefix.equals(PREFIX_MORETHAN);
    }

    /**
     * Gets the keyword of the PersonDetailContainsKeywordPredicate.
     * @return The keyword of the PersonDetailContainsKeywordPredicate.
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * Returns a PersonDetailContainsKeyword&ExamPredicate with the same keyword and prefix.
     * @param exam The exam to be searched.
     * @return A PersonDetailContainsKeyword&ExamPredicate with the same keyword and prefix.
     */
    public PersonDetailContainsKeywordAndExamPredicate withExam(Exam exam) {
        return new PersonDetailContainsKeywordAndExamPredicate(prefix, keyword, exam);
    }
}

