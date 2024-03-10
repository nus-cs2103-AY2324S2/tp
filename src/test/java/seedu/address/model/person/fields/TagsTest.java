package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import seedu.address.model.tag.Tag;

class TagsTest {

    private static final String INVALID_TAG = "#friend";
    private static final String VALID_TAG_1 = "friend";
    private static final String VALID_TAG_2 = "neighbour";

    private static final String[] emptyStringArray = new String[0];

    private static final Tag[] emptyTagArray = new Tag[0];

    @Test
    public void constructor_emptyArray_success() {
        assertEquals(new Tags(emptyTagArray), new Tags(emptyStringArray));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tags.of(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Tags.of(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptyTags() throws Exception {
        assertEquals(new Tags(new Tag[0]), Tags.of(Collections.emptyList()));
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Tags actualTags = Tags.of(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Tags expectedTags = new Tags(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2));

        assertEquals(expectedTags, actualTags);
    }

    @Test
    void equals_null_false() {
        assertNotEquals(null, new Tags(emptyTagArray));
    }

}
