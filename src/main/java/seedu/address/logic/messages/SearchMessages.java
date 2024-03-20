package seedu.address.logic.messages;

/**
 * Container for user delete command visible messages.
 */
public class SearchMessages extends Messages {
    public static final String MESSAGE_SEARCH_PERSON_SUCCESS = "Woof! %1$s contacts found! \uD83D\uDC36";
    public static final String MESSAGE_SEARCH_MISSING_FIELD = "Failed to find Pooch Contact - "
            + "Search requires a name / phone / address / email / product / employment field. \uD83D\uDC3E";
    public static final String MESSAGE_SEARCH_INVALID_FIELD = "Failed to find Pooch Contact - "
            + "Pooch doesn't recognise the field \uD83D\uDC3E";
}
