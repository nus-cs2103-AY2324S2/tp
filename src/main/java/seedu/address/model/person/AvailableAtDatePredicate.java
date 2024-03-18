package seedu.address.model.person;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import static seedu.address.logic.parser.ParserUtil.parseAvailabilities;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.exceptions.ParseException;

public class AvailableAtDatePredicate implements Predicate<Person> {
    private final Set<Availability> keywords;

    public AvailableAtDatePredicate(List<String> keywords) throws ParseException {
        this.keywords = parseAvailabilities(keywords);
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> person.getAvailabilities().stream()
                        .anyMatch(availability -> availability.equals(keyword)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AvailableAtDatePredicate)) {
            return false;
        }

        AvailableAtDatePredicate otherAvailableAtDatePredicate = (AvailableAtDatePredicate) other;
        return keywords.equals(otherAvailableAtDatePredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
    
}
