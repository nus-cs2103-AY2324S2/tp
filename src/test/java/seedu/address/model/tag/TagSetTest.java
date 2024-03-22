package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TagSetTest {
    @Test
    public void isMatch() {
        Tag friendTag = new Tag("friend");
        Tag loverTag = new Tag("lover");
        Tag invalidTag = new Tag("invalid");

        Set<Tag> testSet = new HashSet<Tag>(Arrays.asList(friendTag, loverTag));

        TagSet tagSet = new TagSet(testSet);

        // Same tags - Match
        assertTrue(tagSet.isMatch(testSet));

        // No tags - Match
        assertTrue(tagSet.isMatch(new HashSet<>()));

        // Single tag - Match
        assertTrue(tagSet.isMatch(new HashSet<Tag>(Arrays.asList(friendTag))));

        // Multiple tags - Match
        assertTrue(tagSet.isMatch(new HashSet<Tag>(Arrays.asList(loverTag, friendTag))));

        // Single tag - Mismatch
        assertFalse(tagSet.isMatch(new HashSet<Tag>(Arrays.asList(invalidTag))));

        // Multiple tag - Partial match
        assertFalse(tagSet.isMatch(new HashSet<Tag>(Arrays.asList(loverTag, invalidTag))));
    }

    @Test
    public void equals() {
        Tag friendTag = new Tag("friend");
        Tag loverTag = new Tag("lover");
        Tag invalidTag = new Tag("invalid");

        Set<Tag> testSet = new HashSet<Tag>(Arrays.asList(friendTag, loverTag));

        TagSet tagSet = new TagSet(testSet);

        // Same tagset
        assertTrue(tagSet.equals(tagSet));

        // Different tagset, same values
        assertTrue(tagSet.equals(new TagSet(testSet)));

        // Empty tagset
        assertFalse(tagSet.equals(new TagSet(new HashSet<>())));

        // Partial tagset
        assertFalse(tagSet.equals(new TagSet(new HashSet<Tag>(Arrays.asList(loverTag)))));

        // Additional tag in tagset
        assertFalse(tagSet.equals(new TagSet(new HashSet<>(Arrays.asList(friendTag, loverTag, invalidTag)))));
    }
}
