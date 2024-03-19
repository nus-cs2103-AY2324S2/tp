package seedu.address.model.person;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Represents a Predicate for filtering {@code Person} objects based on keyword matches in their categories,
 * descriptions, or tags. This allows for complex queries against a person's attributes to be executed in a
 * streamlined manner. The predicate evaluates to true if any of the person's entries match the specified
 * categories or descriptions, or if any of the person's tags match the specified tags.
 *
 * <p>Each person's entry is checked against a map of category descriptions, where the key is the category and
 * the value is the description to match. Additionally, a set of tags can be provided to further filter the
 * persons based on the tags associated with them.
 */
public class PersonFieldsContainKeywordPredicate implements Predicate<Person> {
    private final Map<String, String> categoryDescriptionMap;
    private final Set<String> tags;
    /**
     * Constructs a {@code PersonFieldsContainKeywordPredicate} with the specified category-description mappings
     * and tags.
     *
     * @param categoryDescriptionMap A map where each key-value pair consists of a category and its corresponding
     *                                description to match against a person's entries.
     * @param tags A set of tags to match against a person's tags.
     */
    public PersonFieldsContainKeywordPredicate(Map<String, String> categoryDescriptionMap, Set<String> tags) {
        this.categoryDescriptionMap = categoryDescriptionMap;
        this.tags = tags;
    }
    /**
     * Tests whether a given {@code Person} matches any of the category-description pairs or tags specified in this
     * predicate.
     *
     * @param person The {@code Person} to test.
     * @return True if the person matches any of the specified category-description pairs or tags, otherwise false.
     */
    @Override
    public boolean test(Person person) {
        boolean matchesCategoryDescription = categoryDescriptionMap.entrySet().stream().anyMatch(entry -> {
            Optional<String> personCategoryDescription = Optional.ofNullable(person.getEntry(entry.getKey().trim()))
                    .map(Entry::getDescription)
                    .map(String::toLowerCase);
            return personCategoryDescription.map(description ->
                    description.contains(entry.getValue().toLowerCase())).orElse(false);
        });
        boolean matchesTags = person.getTags().stream().anyMatch(tag -> tags.contains(tag.tagName.toLowerCase()));
        return tags.isEmpty() ? matchesCategoryDescription
                : !categoryDescriptionMap.isEmpty()
                ? matchesTags && matchesCategoryDescription
                : matchesTags;
    }
    /**
     * Compares this predicate with another object to determine if they are equal.
     *
     * @param other The object to compare this predicate against.
     * @return True if the other object is a {@code PersonFieldsContainKeywordPredicate} with the same
     *         category-description map and tags, otherwise false.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PersonFieldsContainKeywordPredicate)) {
            return false;
        }
        PersonFieldsContainKeywordPredicate otherPredicate = (PersonFieldsContainKeywordPredicate) other;
        return categoryDescriptionMap.equals(otherPredicate.categoryDescriptionMap) && tags.equals(otherPredicate.tags);
    }
    /**
     * Returns a string representation of this predicate, including the category-description map and tags.
     *
     * @return A string representation of this predicate.
     */
    @Override
    public String toString() {
        return "PersonFieldsContainKeywordPredicate{"
            + "categoryDescriptionMap=" + categoryDescriptionMap
            + ", tags=" + tags
            + '}';
    }
}
