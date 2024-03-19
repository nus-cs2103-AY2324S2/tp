package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TagStatusTest {

    @Test
    void getTagStatus() {

        final String emptyTagStatus = "";

        assertTrue(TagStatus.COMPLETE_GOOD.equals(TagStatus.getTagStatus(
                TagStatus.COMPLETE_GOOD_KEYWORD)));
        assertTrue(TagStatus.COMPLETE_BAD.equals(TagStatus.getTagStatus(
                TagStatus.COMPLETE_BAD_KEYWORD)));
        assertTrue(TagStatus.INCOMPLETE_GOOD.equals(TagStatus.getTagStatus(
                TagStatus.INCOMPLETE_GOOD_KEYWORD)));
        assertTrue(TagStatus.INCOMPLETE_BAD.equals(TagStatus.getTagStatus(
                TagStatus.INCOMPLETE_BAD_KEYWORD)));
        assertTrue(TagStatus.DEFAULT_STATUS.equals(TagStatus.getTagStatus(
                emptyTagStatus)));
    }
}
