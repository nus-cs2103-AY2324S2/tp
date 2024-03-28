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

        // Searches through staff only
        if (hasField(PREFIX_SALARY) || hasField(PREFIX_EMPLOYMENT)) {
            if (!(person instanceof Staff)) {
                return false;
            }

            Staff staff = (Staff) person;
            return checkNamePhoneEmailAndAddress(staff)
                    && checkSalary(staff)
                    && checkEmployment(staff);
        }

        // Searches through supplier only
        if (hasField(PREFIX_PRICE) || hasField(PREFIX_PRODUCT)) {
            if (!(person instanceof Supplier)) {
                return false;
            }

            Supplier supplier = (Supplier) person;
            return checkNamePhoneEmailAndAddress(supplier)
                    && checkPrice(supplier)
                    && checkProduct(supplier);
        }

        // Searches through maintainer only
        if (hasField(PREFIX_SKILL) || hasField(PREFIX_COMMISSION)) {
            if (!(person instanceof Maintainer)) {
                return false;
            }

            Maintainer maintainer = (Maintainer) person;
            return checkNamePhoneEmailAndAddress(maintainer)
                    && checkSkill(maintainer)
                    && checkCommission(maintainer);
        }

        // No specialty
        return checkNamePhoneEmailAndAddress(person);
    }

    boolean hasField(Prefix field) {
        return keywords.getValue(field).isPresent();
    }

    String getValue(Prefix field) {
        return keywords.getValue(field).get();
    }

    boolean contains(String identifier, Prefix field) {
        if (!hasField(field)) {
            return true;
        }

        String lowerCasedIdentifier = identifier.toLowerCase();
        String query = getValue(field);
        String lowerCasedQuery = query.toLowerCase();
        return lowerCasedIdentifier.contains(lowerCasedQuery);
    }

    boolean checkName(Person person) {
        return contains(person.getName().toString(), PREFIX_NAME);
    }

    boolean checkPhone(Person person) {
        return contains(person.getPhone().toString(), PREFIX_PHONE);
    }

    boolean checkEmail(Person person) {
        return contains(person.getEmail().toString(), PREFIX_EMAIL);
    }

    boolean checkAddress(Person person) {
        return contains(person.getAddress().toString(), PREFIX_ADDRESS);
    }

    boolean checkSalary(Staff staff) {
        return contains(staff.getSalary().toString(), PREFIX_SALARY);
    }

    boolean checkEmployment(Staff staff) {
        return contains(staff.getEmployment().toString(), PREFIX_EMPLOYMENT);
    }

    boolean checkPrice(Supplier supplier) {
        return contains(supplier.getPrice().toString(), PREFIX_PRICE);
    }

    boolean checkProduct(Supplier supplier) {
        return contains(supplier.getProduct().toString(), PREFIX_PRODUCT);
    }

    boolean checkSkill(Maintainer maintainer) {
        return contains(maintainer.getSkill().toString(), PREFIX_SKILL);
    }

    boolean checkCommission(Maintainer maintainer) {
        return contains(maintainer.getCommission().toString(), PREFIX_COMMISSION);
    }

    boolean checkNamePhoneEmailAndAddress(Person person) {
        return checkName(person)
                && checkPhone(person)
                && checkEmail(person)
                && checkAddress(person);
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
