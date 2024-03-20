package seedu.address.model.group;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.group.Group;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class GroupContainsKeywordsPredicate implements Predicate<Person> {

    private final List<Group> groups;

    /**
     * Returns a TagContainsKeywordsPredicate object by taking a list of the tag names.
     */
    public GroupContainsKeywordsPredicate(List<String> keywords) {
        this.groups = keywords.stream().map(Group::new).collect(Collectors.toList());
    }

    @Override
    public boolean test(Person person) {
        Set<Group> personGroup = person.getGroups();
        return groups.stream()
                .anyMatch(personGroup::contains);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupContainsKeywordsPredicate)) {
            return false;
        }

        GroupContainsKeywordsPredicate otherGroupContainsKeywordsPredicate = (GroupContainsKeywordsPredicate) other;
        return groups.equals(otherGroupContainsKeywordsPredicate.groups);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("groups", groups).toString();
    }
//    private final List<String> keywords;
//
//    public GroupContainsKeywordsPredicate(List<String> keywords) {
//        this.keywords = keywords;
//    }
//
//    @Override
//    public boolean test(Person person) {
//        for (Group group: person.getGroups()) {
//            if (keywords.stream()
//                    .anyMatch(keyword -> StringUtil.containsStartingWordIgnoreCase(group.groupName, keyword)) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        return other == this // short circuit if same object
//                || (other instanceof GroupContainsKeywordsPredicate // instanceof handles nulls
//                && keywords.equals(((GroupContainsKeywordsPredicate) other).keywords)); // state check
//    }
//

}