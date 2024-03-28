package seedu.edulink.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_MAJOR = new Prefix("m/");
    public static final Prefix PREFIX_ID = new Prefix("id/");
    public static final Prefix PREFIX_INTAKE = new Prefix("in/");
    public static final Prefix PREFIX_FILENAME = new Prefix("f/");
}
