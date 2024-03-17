package scm.address.model.filename;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import scm.address.model.tag.Tag;

public class FilenameTest {
    private static final String VALID_FILENAME = "hello";
    private static final String VALID_TAG = "tag";

    @Test
    public void testEquals_sameObject_success() {
        Filename filename = new Filename(VALID_FILENAME);
        assertEquals(filename, filename);
    }

    @Test
    public void testEquals_differentObjectType_failure() {
        Filename filename = new Filename(VALID_FILENAME);
        Tag tag = new Tag(VALID_TAG);
        assertFalse(filename.equals(tag));
    }

    @Test
    public void test_toString_success() {
        Filename filename = new Filename(VALID_FILENAME);
        assertEquals(VALID_FILENAME, filename.toString());
    }
}
