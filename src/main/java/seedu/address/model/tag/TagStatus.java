package seedu.address.model.tag;

/**
 * Represents submission / attendance status of a tag
 */
public enum TagStatus {
    // complete before deadline
    COMPLETE_GOOD,
    // complete after deadline
    COMPLETE_BAD,
    // incomplete before deadline
    INCOMPLETE_GOOD,
    // incomplete after deadline
    INCOMPLETE_BAD
}
