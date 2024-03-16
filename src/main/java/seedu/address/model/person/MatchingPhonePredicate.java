package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Phone} matches the input.
 */
public class MatchingPhonePredicate implements Predicate<Person> {
    private final List<String> numbers;

    public MatchingPhonePredicate(List<String> numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean test(Person person) {
        return numbers.stream()
                .anyMatch(number -> StringUtil.containsWordIgnoreCase(person.getPhone().value, number));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchingPhonePredicate)) {
            return false;
        }

        MatchingPhonePredicate otherMatchingPhonePredicate = (MatchingPhonePredicate) other;
        return this.numbers.equals(otherMatchingPhonePredicate.numbers);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", this.numbers).toString();
    }
}
