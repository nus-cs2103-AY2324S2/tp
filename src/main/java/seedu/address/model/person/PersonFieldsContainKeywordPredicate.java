package seedu.address.model.person;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Person}'s entries match any of the categories, descriptions given, or tags.
 */
public class PersonFieldsContainKeywordPredicate implements Predicate<Person> {
    private final Map<String, String> categoryDescriptionMap;
    private final Set<String> tags;
    
    public PersonFieldsContainKeywordPredicate(Map<String, String> categoryDescriptionMap, Set<String> tags) {
        this.categoryDescriptionMap = categoryDescriptionMap;
        this.tags = tags;
    }
    
    @Override
    public boolean test(Person person) {
        
        boolean matchesCategoryDescription = categoryDescriptionMap.entrySet().stream()
            .anyMatch(entry -> {
                Optional<String> personCategoryDescription = Optional.ofNullable(person.getEntry(entry.getKey().trim()))
                    .map(Entry::getDescription)
                    .map(String::toLowerCase);
                return personCategoryDescription.map(description -> description.contains(entry.getValue().toLowerCase()))
                    .orElse(false);
            });
        
        boolean matchesTags = person.getTags().stream()
            .anyMatch(tag -> tags.contains(tag.tagName.toLowerCase()));
        
        return tags.isEmpty()
                ? matchesCategoryDescription
                : !categoryDescriptionMap.isEmpty()
                ? matchesTags && matchesCategoryDescription
                : matchesTags;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        
        if (!(other instanceof PersonFieldsContainKeywordPredicate)) {
            return false;
        }
        
        PersonFieldsContainKeywordPredicate otherPredicate = (PersonFieldsContainKeywordPredicate) other;
        return categoryDescriptionMap.equals(otherPredicate.categoryDescriptionMap) &&
            tags.equals(otherPredicate.tags);
    }
    
    @Override
    public String toString() {
        // Implement more detailed toString if necessary
        return "PersonFieldsContainKeywordPredicate{" +
            "categoryDescriptionMap=" + categoryDescriptionMap +
            ", tags=" + tags +
            '}';
    }
}