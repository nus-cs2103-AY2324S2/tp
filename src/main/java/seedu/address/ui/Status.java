package seedu.address.ui;

/**
 * Monitors the fields that have been entered by the user when
 * they key in fields for the add command by by step.
 */
public enum Status {
    GET_NAME, GET_NUMBER, GET_EMAIL, GET_ADDRESS, GET_TAG, COMPLETE, COPY;

    @Override
    public String toString() {
        String msg = "";
        switch(this) {
        case GET_NAME:
            msg = "name";
            break;
        case GET_NUMBER:
            msg = "number";
            break;
        case GET_EMAIL:
            msg = "email";
            break;
        case GET_ADDRESS:
            msg = "address";
            break;
        case GET_TAG:
            msg = "tag (optional)";
            break;
        case COMPLETE:
            msg = "complete command";
            break;
        case COPY:
            msg = "copy";
            break;
        default:
            assert false : "The enum should not reach this stage";
        }
        return msg;

    }
}
