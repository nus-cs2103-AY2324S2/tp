package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.ClearCommand;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TypePredicate implements Predicate<Person> {
    private final String type;

    public TypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(Person person) {
        if (type.equals("housekeeper")) {
            return !person.isClient();
        } else if (type.equals("client")) {
            return person.isClient();
        } else {
            return false;
        }
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

        TypePredicate otherNameContainsKeywordsPredicate = (TypePredicate) other;
        return type.equals(otherNameContainsKeywordsPredicate.type);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("type", type).toString();
    }
}
