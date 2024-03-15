package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonFieldsContainKeywordPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public PersonFieldsContainKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
            .anyMatch(keyword ->
                StringUtil.containsWordIgnoreCase(person.getEntry("Name").getDescription(), keyword) ||
                    StringUtil.containsWordIgnoreCase(person.getEntry("Phone").getDescription(), keyword) ||
                    StringUtil.containsWordIgnoreCase(person.getEntry("Address").getDescription(), keyword) ||
                    StringUtil.containsWordIgnoreCase(person.getEntry("Email").getDescription(), keyword) ||
                    person.getTags().stream().anyMatch(tag -> StringUtil.containsWordIgnoreCase(tag.tagName, keyword))
            );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        
        if (!(other instanceof PersonFieldsContainKeywordPredicate)) {
            return false;
        }

        PersonFieldsContainKeywordPredicate otherPerson = (PersonFieldsContainKeywordPredicate) other;
        return keywords.equals(otherPerson.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
