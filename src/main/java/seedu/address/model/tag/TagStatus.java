package seedu.address.model.tag;

/**
 * Represents submission / attendance status of a tag.
 */
public enum TagStatus {
    COMPLETE_GOOD, // complete before deadline
    COMPLETE_BAD, // complete after deadline
    INCOMPLETE_GOOD, // incomplete before deadline
    INCOMPLETE_BAD; // incomplete after deadline

    /**
     * @param status Keyword corresponding each of the TagStatus.
     * @return TagStatus matching the keyword.
     */
    public static TagStatus getTagStatus(String status) {
        switch (status) {
            case "cg":
                return TagStatus.COMPLETE_GOOD;
            case "cb":
                return TagStatus.COMPLETE_BAD;
            case "ig":
                return TagStatus.INCOMPLETE_GOOD;
            case "ib":
                return TagStatus.INCOMPLETE_BAD;
            default:
                return TagStatus.INCOMPLETE_GOOD;
        }
    }

}
