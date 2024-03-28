package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple
 * commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_INTEREST = new Prefix("i/");

    public static final Prefix PREFIX_SCHEDULE = new Prefix("s/");
    public static final Prefix PREFIX_START = new Prefix("start/");
    public static final Prefix PREFIX_END = new Prefix("end/");

    public static final Prefix PREFIX_SPECIFIC_PARTICIPANTS = new Prefix("participants/");
    public static final Prefix PREFIX_ADD_PARTICIPANTS = new Prefix("add/");
    public static final Prefix PREFIX_REMOVE_PARTICIPANTS = new Prefix("remove/");

}
