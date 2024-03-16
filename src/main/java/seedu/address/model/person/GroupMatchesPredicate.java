package seedu.address.model.person;

import seedu.address.model.group.Group;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Tests if a keyword matches any {@code Person}'s {@code Groups}
 */
public class GroupMatchesPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public GroupMatchesPredicate(List<String> s) {
        keywords = s;
    }

    /**
     * Returns if the person is in ALL the groups provided
     * in the keywords based on an EXACT match.
     *
     * @param person the person we are concerned with
     * @return if this person has the groups we are searching for
     */
    @Override
    public boolean test(Person person) {
        // Empty set of keywords means we accept any person regardless of their group
        if (keywords.isEmpty()) {
            return true;
        }

        Set<Group> groups = person.getGroups();
        Set<String> groupNames = groups.stream().map(x -> x.groupName).collect(Collectors.toSet());

        boolean containsAllKeywords = true;
        for (String k : keywords) {
            if (!groupNames.contains(k)) {
                containsAllKeywords = false;
                break;
            }
        }

        return containsAllKeywords;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupMatchesPredicate)) {
            return false;
        }

        GroupMatchesPredicate e = (GroupMatchesPredicate) other;
        return keywords.equals(e.keywords);
    }
}
