package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n\\");
    public static final Prefix PREFIX_PHONE = new Prefix("p\\");
    public static final Prefix PREFIX_EMAIL = new Prefix("e\\");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a\\");
    public static final Prefix PREFIX_TAG = new Prefix("t\\");
    public static final Prefix PREFIX_DOB = new Prefix("dob\\");
    public static final Prefix PREFIX_WARD_NO = new Prefix("w\\");
    public static final Prefix PREFIX_IC = new Prefix("ic\\");
    public static final Prefix PREFIX_ADMISSION_DATE = new Prefix("ad\\");
}
