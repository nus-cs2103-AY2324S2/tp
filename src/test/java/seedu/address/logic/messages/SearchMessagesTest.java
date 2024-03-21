package seedu.address.logic.messages;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SearchMessagesTest {
    @Test
    public void testInvalidSearchFieldMessage() {
        String errorMessage = SearchMessages.MESSAGE_SEARCH_INVALID_FIELD;

        assertEquals("Failed to find Pooch Contact - Pooch doesn't recognise the field \uD83D\uDC3E", errorMessage);
    }
}
