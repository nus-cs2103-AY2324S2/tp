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
//        Predicate<String> personName = keyword ->
//                StringUtil.containsWordIgnoreCase(person.getName().toString(), keyword);
//        Predicate<String> personNumber = keyword ->
//                StringUtil.containsWordIgnoreCase(person.getPhone().toString(), keyword);
//        Predicate<String> personAddress = keyword ->
//                StringUtil.containsWordIgnoreCase(person.getAddress().toString(), keyword);
//        Predicate<String> personEmail = keyword ->
//                StringUtil.containsWordIgnoreCase(person.getEmail().toString(), keyword);
//        Predicate<String> personTag = keyword -> StringUtil.containsWordIgnoreCase(person.getTags().stream()
//                .map(tag -> tag.tagName).collect(Collectors.joining()), keyword);

//        Predicate<String> personName = keyword ->
//                keyword.regionMatches(true, 0, person.getName().toString(),
//                        0, keyword.length());
//        Predicate<String> personNumber = keyword ->
//                keyword.regionMatches(true, 0, person.getPhone().toString(),
//                        0, keyword.length());
//        Predicate<String> personAddress = keyword ->
//                keyword.regionMatches(true, 0, person.getAddress().toString(),
//                        0, keyword.length());
//        Predicate<String> personEmail = keyword ->
//                keyword.regionMatches(true, 0, person.getEmail().toString(),
//                        0, keyword.length());
//        Predicate<String> personTag = keyword -> keyword.regionMatches(true, 0, person.getTags().stream()
//                .map(tag -> tag.tagName).collect(Collectors.joining()), 0, keyword.length());

        Predicate<String> personName = keyword ->
                person.getName().toString().toLowerCase().contains(keyword.toLowerCase());
        Predicate<String> personNumber = keyword ->
                person.getPhone().toString().toLowerCase().contains(keyword.toLowerCase());
        Predicate<String> personAddress = keyword ->
                person.getAddress().toString().toLowerCase().contains(keyword.toLowerCase());
        Predicate<String> personEmail = keyword ->
                person.getEmail().toString().toLowerCase().contains(keyword.toLowerCase());
        Predicate<String> personTag = keyword -> person.getTags().stream()
                .map(tag -> tag.tagName).collect(Collectors.joining()).toLowerCase().contains(keyword.toLowerCase());

        Predicate<String> combinedCondition = personName.or(personNumber)
                .or(personAddress).or(personEmail).or(personTag);
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
