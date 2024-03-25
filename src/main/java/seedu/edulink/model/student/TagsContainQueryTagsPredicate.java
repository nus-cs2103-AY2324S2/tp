package seedu.edulink.model.student;

import java.util.Set;
import java.util.function.Predicate;

import seedu.edulink.commons.util.StringUtil;
import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Tags} matches the query Tags.
 */
public class TagsContainQueryTagsPredicate implements Predicate<Student> {

    private final Set<Tag> filterTagList;

    public TagsContainQueryTagsPredicate(Set<Tag> tagList) {
        this.filterTagList = tagList;
    }

    @Override
    public boolean test(Student student) {
        boolean isMatch = true;
        Set<Tag> studentTagList = student.getTags();

        if (studentTagList.size() < filterTagList.size()) {
            return false;
        }

        for (Tag filterTag: filterTagList) {
            isMatch = studentTagList.stream()
                    .anyMatch(studentTag -> StringUtil.matchesIgnoreCase(studentTag.tagName, filterTag.tagName));
            if (!isMatch) {
                break;
            }
        }
        return isMatch;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagsContainQueryTagsPredicate)) {
            return false;
        }

        TagsContainQueryTagsPredicate otherTagsContainQueryTagsPredicate = (TagsContainQueryTagsPredicate) other;
        return filterTagList.equals(otherTagsContainQueryTagsPredicate.filterTagList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Tags: ", filterTagList.toString()).toString();
    }
}
