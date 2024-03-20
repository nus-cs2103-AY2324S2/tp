package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    // TODO: Change and remove this
    public static final Prefix PREFIX_ADDRESS = new Prefix("t/");
    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_PATIENT_ID = new Prefix("pid/");
    public static final Prefix PREFIX_DATETIME = new Prefix("d/");
    public static final Prefix PREFIX_ATTEND = new Prefix("att/");
    //TODO: remove after case log is implemented
    public static final Prefix PREFIX_APPOINTMENT_DESCRIPTION = new Prefix("ad/");
    public static final Prefix PREFIX_APPOINTMENT_ID = new Prefix("aid/");

}
