package seedu.address.ui;

/**
 * Monitors the fields that have been entered by the user when
 * they key in fields for the add command by by step.
 */
public enum Status {
    GET_NAME, GET_NUMBER, GET_EMAIL, GET_ADDRESS, GET_TAG, COMPLETE;

    @Override
    public String toString() {
        String msg = "";
        switch(this) {
        case GET_NAME:
            msg = "Successfully entered a valid name, please enter number next.";
            break;
        case GET_NUMBER:
            msg = "Successfully entered a valid naumber, please enter email next.";
            break;
        case GET_EMAIL:
            msg = "Successfully entered a valid email, please enter address next.";
            break;
        case GET_ADDRESS:
            msg = "Successfully entered a valid address, please enter 0 or 1 tag next.";
            break;
        case GET_TAG:
            msg = "Successfully entered all necessary fields.Please press ENTER to show the whole command.";
            break;
        default:
            assert false : "The enum should not reach this stage";
        }
        return msg;

    }
}
