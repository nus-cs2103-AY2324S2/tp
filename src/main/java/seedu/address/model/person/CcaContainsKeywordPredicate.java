package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.cca.Cca;


/**
 * Tests that a {@code Person}'s {@code tags} matches any of the keywords given.
 */
public class CcaContainsKeywordPredicate implements Predicate<Person> {
    private final List<Cca> ccas;

    /**
     * Returns a TagContainsKeywordsPredicate object by taking a list of the tag names.
     */
    public CcaContainsKeywordPredicate(List<String> keywords) {

        this.ccas = keywords.stream().map(Cca::new).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<Cca> personCcas = person.getCcas();
        return ccas.stream()
                .anyMatch(personCcas::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CcaContainsKeywordPredicate)) {
            return false;
        }

        CcaContainsKeywordPredicate otherNameContainsKeywordsPredicate = (CcaContainsKeywordPredicate) other;
        return ccas.equals(otherNameContainsKeywordsPredicate.ccas);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("ccas", ccas).toString();
    }
}
