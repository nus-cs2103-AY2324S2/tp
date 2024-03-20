package seedu.realodex.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_INCOME = new Prefix("i/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");

    //To remove EMAIl field soon
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_FAMILY = new Prefix("f/");

    //Possible rename to additional notes to fit feature spec
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_REMARK = new Prefix("r/");



}
