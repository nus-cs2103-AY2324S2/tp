package seedu.address.model.person;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.ArgumentMultimap;

import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Tests that a {@code Person}'s {@code Details} matches any of the keywords given.
 */
public class KeywordPredicate implements Predicate<Person> {
    private final ArgumentMultimap keywords;

    public KeywordPredicate(ArgumentMultimap keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        boolean predicate = true;

        if (keywords.getValue(PREFIX_NAME).isPresent()) {
            predicate &= person.getName().fullName.contains(keywords.getValue(PREFIX_NAME).get());
        }

        if (keywords.getValue(PREFIX_PHONE).isPresent()) {
            predicate &= person.getPhone().toString().contains(keywords.getValue(PREFIX_PHONE).get());
        }

        if (keywords.getValue(PREFIX_EMAIL).isPresent()) {
            predicate &= person.getEmail().toString().contains(keywords.getValue(PREFIX_EMAIL).get());
        }

        if (keywords.getValue(PREFIX_ADDRESS).isPresent()) {
            predicate &= person.getAddress().toString().contains(keywords.getValue(PREFIX_ADDRESS).get());
        }

        if (person instanceof Staff) {
            Staff staff = (Staff) person;
            if (keywords.getValue(PREFIX_SALARY).isPresent()) {
                predicate &= staff.getSalary().toString().contains(keywords.getValue(PREFIX_SALARY).get());
            }

            if (keywords.getValue(PREFIX_EMPLOYMENT).isPresent()) {
                predicate &= staff.getEmployment().toString().contains(keywords.getValue(PREFIX_EMPLOYMENT).get());
            }
        }

        if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            if (keywords.getValue(PREFIX_PRICE).isPresent()) {
                predicate &= supplier.getPrice().toString().contains(keywords.getValue(PREFIX_PRICE).get());
            }

            if (keywords.getValue(PREFIX_PRODUCT).isPresent()) {
                predicate &= supplier.getProduct().toString().contains(keywords.getValue(PREFIX_PRODUCT).get());
            }
        }

        if (person instanceof Maintainer) {
            Maintainer maintainer = (Maintainer) person;
            if (keywords.getValue(PREFIX_SKILL).isPresent()) {
                predicate &= maintainer.getSkill().toString().contains(keywords.getValue(PREFIX_SALARY).get());
            }

            if (keywords.getValue(PREFIX_COMMISSION).isPresent()) {
                predicate &= maintainer.getCommission().toString().contains(keywords.getValue(PREFIX_COMMISSION).get());
            }
        }

        return predicate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof KeywordPredicate)) {
            return false;
        }

        KeywordPredicate otherKeywordPredicate = (KeywordPredicate) other;
        return keywords.equals(otherKeywordPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
