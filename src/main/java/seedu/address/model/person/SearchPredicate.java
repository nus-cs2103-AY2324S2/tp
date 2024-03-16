package seedu.address.model.person;

import seedu.address.logic.commands.SearchCommand;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class SearchPredicate implements Predicate<Person> {
    private List<ContainsKeywordsPredicate> predicateList;

    public SearchPredicate(List<ContainsKeywordsPredicate> predicateList) {
        this.predicateList = predicateList;
    }

    @Override
    public boolean test(Person person) {
        boolean result = true;
        for (ContainsKeywordsPredicate predicate : predicateList) {
            result = result && predicate.test(person);
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SearchPredicate)) {
            return false;
        }

        SearchPredicate otherSearchPredicate = (SearchPredicate) other;
        return predicateList.equals(otherSearchPredicate.predicateList);
    }
}
