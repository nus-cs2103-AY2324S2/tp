package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

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

        predicate &= contains(person.getName().toString(), PREFIX_NAME);
        predicate &= contains(person.getPhone().toString(), PREFIX_PHONE);
        predicate &= contains(person.getEmail().toString(), PREFIX_EMAIL);
        predicate &= contains(person.getAddress().toString(), PREFIX_ADDRESS);

        if (person instanceof Staff) {
            Staff staff = (Staff) person;
            predicate &= contains(staff.getSalary().toString(), PREFIX_SALARY);
            predicate &= contains(staff.getEmployment().toString(), PREFIX_EMPLOYMENT);
        }

        if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            predicate &= contains(supplier.getPrice().toString(), PREFIX_PRICE);
            predicate &= contains(supplier.getProduct().toString(), PREFIX_PRODUCT);
        }

        if (person instanceof Maintainer) {
            Maintainer maintainer = (Maintainer) person;
            predicate &= contains(maintainer.getSkill().toString(), PREFIX_SKILL);
            predicate &= contains(maintainer.getCommission().toString(), PREFIX_COMMISSION);
        }

        return predicate;
    }

    boolean contains(String identifier, Prefix keyword) {
        if (keywords.getValue(keyword).isPresent()) {
            return identifier.toLowerCase().contains(keywords.getValue(keyword).get().toLowerCase());
        }

        return true;
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
        return new ToStringBuilder(this).add("keyword", keywords).toString();
    }
}
