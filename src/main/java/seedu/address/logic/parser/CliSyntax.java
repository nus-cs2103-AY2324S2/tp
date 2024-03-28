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

    /* New Prefix Definitions */
    public static final Prefix PREFIX_COMPANY = new Prefix("/com");
    public static final Prefix PREFIX_CONTACT_NAME = new Prefix("/poc");
    public static final Prefix PREFIX_CONTACT_EMAIL = new Prefix("/email");
    public static final Prefix PREFIX_CONTACT_NUMBER = new Prefix("/phone");
    public static final Prefix PREFIX_LOCATION = new Prefix("/loc");
    public static final Prefix PREFIX_STATUS = new Prefix("/status");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("/desc");
    public static final Prefix PREFIX_ROLE = new Prefix("/role");
    public static final Prefix PREFIX_REMARK = new Prefix("/remark");
    public static final Prefix PREFIX_TASK = new Prefix("/task");
    public static final Prefix PREFIX_SELECT_TASK = new Prefix("/selecttask");
    public static final Prefix PREFIX_DEADLINE = new Prefix("/deadline");
}
