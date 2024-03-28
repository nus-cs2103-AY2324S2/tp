package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("; name : ");
    public static final Prefix PREFIX_PHONE = new Prefix("; phone : ");
    public static final Prefix PREFIX_EMAIL = new Prefix("; email : ");
    public static final Prefix PREFIX_ADDRESS = new Prefix("; address : ");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_EMPLOYMENT = new Prefix("; employment : ");
    public static final Prefix PREFIX_SALARY = new Prefix("; salary : ");
    public static final Prefix PREFIX_PRODUCT = new Prefix("; product : ");
    public static final Prefix PREFIX_PRICE = new Prefix("; price : ");
    public static final Prefix PREFIX_SKILL = new Prefix("; skill : ");
    public static final Prefix PREFIX_COMMISSION = new Prefix("; commission : ");

    public static final Prefix PREFIX_FIELD = new Prefix("; field : ");
    public static final Prefix PREFIX_NOTE = new Prefix("; note : ");
    public static final Prefix PREFIX_RATING = new Prefix("; rating : ");
    public static final Prefix PREFIX_HELP = new Prefix("; command : ");
    public static final Prefix PREFIX_DEADLINE = new Prefix("; deadline : ");

    public static final Prefix[] PREFIX_COLLECTION = new Prefix[] {
        PREFIX_NAME,
        PREFIX_PHONE,
        PREFIX_EMAIL,
        PREFIX_ADDRESS,
        PREFIX_TAG,
        PREFIX_SALARY,
        PREFIX_EMPLOYMENT,
        PREFIX_PRICE,
        PREFIX_PRODUCT,
        PREFIX_SKILL,
        PREFIX_COMMISSION,
        PREFIX_NOTE,
        PREFIX_RATING,
        PREFIX_DEADLINE,
        PREFIX_HELP
    };
}
