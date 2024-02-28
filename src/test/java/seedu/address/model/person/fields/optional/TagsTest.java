package seedu.address.model.person.fields.optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

class TagsTest {

    private static final String[] emptyStringArray = new String[0];

    private static final Tag[] emptyTagArray = new Tag[0];

    @Test
    public void constructor_emptyArray_success() {
        assertEquals(new Tags(emptyTagArray), new Tags(emptyStringArray));
    }

    @Test
    void equals_null_false() {
        assertNotEquals(null, new Tags(emptyTagArray));
    }

}
