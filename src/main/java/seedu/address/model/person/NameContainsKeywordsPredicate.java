package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;


    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Predicate<String> personName = keyword -> StringUtil.containsWordIgnoreCase(person.getName().toString(), keyword);
        Predicate<String> personNumber = keyword -> StringUtil.containsWordIgnoreCase(person.getPhone().toString(), keyword);
        Predicate<String> personAddress = keyword -> StringUtil.containsWordIgnoreCase(person.getAddress().toString(), keyword);
        Predicate<String> personEmail = keyword -> StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword);
        Predicate<String> personTag = keyword -> StringUtil.containsWordIgnoreCase(person.getTags().stream()
                .map(tag -> tag.tagName).collect(Collectors.joining()), keyword);
        //System.out.println(person.getName());
        //Set<Tag> tags = person.getTags();
        //for (Tag tag : tags) {
        //    System.out.println(tag.tagName);
        //}
        //System.out.println();
        //System.out.println(person.getTags().stream().map(tag -> tag.tagName));
        Predicate<String> combinedCondition = personName.or(personNumber).or(personAddress).or(personEmail).or(personTag);
        return keywords.stream()
                .anyMatch(combinedCondition);
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

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
