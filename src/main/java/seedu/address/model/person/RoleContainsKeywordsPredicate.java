package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Represents a predicate that checks if a person's role contains a specified keyword.
 * This predicate is used to filter a list of persons based on their roles.
 */
public class RoleContainsKeywordsPredicate implements Predicate<Person> {
    private final String keyword;

    /**
     * Constructs a {@code RoleContainsKeywordsPredicate} with the specified keyword.
     *
     * @param keyword The keyword to match against the person's role.
     */
    public RoleContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Tests if the given person's role contains the keyword.
     *
     * @param person The person to test.
     * @return {@code true} if the person's role contains the keyword, {@code false} otherwise.
     */
    @Override
    public boolean test(Person person) {
        return person.getRole().toLowerCase().equals(keyword);
    }

    /**
     * Checks if this predicate is equal to another object.
     *
     * @param other The object to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof RoleContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((RoleContainsKeywordsPredicate) other).keyword));
    }
}
