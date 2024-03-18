package seedu.address.model.module;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code ModuleCode} matches the keyword given.
 */
public class ModuleContainsKeywordPredicate implements Predicate<Person> {

    private final String keyword;

    public ModuleContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Person person) {
        return StringUtil.containsPartialWordIgnoreCase(person.getModule().value, keyword);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleContainsKeywordPredicate)) {
            return false;
        }

        ModuleContainsKeywordPredicate otherModuleContainsKeywordPredicate = (ModuleContainsKeywordPredicate) other;
        return keyword.equals(otherModuleContainsKeywordPredicate.keyword);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keyword", keyword).toString();
    }
}
