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
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_TITLE = new Prefix("T/");
    public static final Prefix PREFIX_AUTHOR = new Prefix("A/");
    public static final Prefix PREFIX_PUBLICATION_DATE = new Prefix("D/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("C/");
    public static final Prefix PREFIX_STATUS = new Prefix("S/");
    public static final Prefix PREFIX_METRIC = new Prefix("M/");

}
