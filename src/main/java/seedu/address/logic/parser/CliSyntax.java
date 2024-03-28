package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_POLICY = new Prefix("po/");
    public static final Prefix PREFIX_EXPIRY_DATE = new Prefix("ed/");
    public static final Prefix PREFIX_PREMIUM = new Prefix("pm/");
    public static final Prefix PREFIX_STATUS = new Prefix("s/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_RELATIONSHIP = new Prefix("r/"); // Add this line

    public static final Prefix PREFIX_MEETING_DATE = new Prefix("md/");
    public static final Prefix PREFIX_MEETING_TIME = new Prefix("mt/");
    public static final Prefix PREFIX_MEETING_AGENDA = new Prefix("ma/");
    public static final Prefix PREFIX_MEETING_NOTES = new Prefix("mn/");

    public static final Prefix PREFIX_MEETING_DURATION = new Prefix("mdur/");

}
