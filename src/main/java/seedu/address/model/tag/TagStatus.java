package seedu.address.model.tag;

/**
 * Represents submission / attendance status of a tag
 */
public enum TagStatus {
    COMPLETE_GOOD, // complete before deadline
    COMPLETE_BAD, // complete after deadline
    INCOMPLETE_GOOD, // incomplete before deadline
    INCOMPLETE_BAD // incomplete after deadline
}
