package seedu.realodex.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/", "name");
    public static final Prefix PREFIX_NAME_CAPS = new Prefix("N/", "name");
    public static final Prefix PREFIX_PHONE = new Prefix("p/", "phone");
    public static final Prefix PREFIX_PHONE_CAPS = new Prefix("P/", "phone");
    public static final Prefix PREFIX_INCOME = new Prefix("i/", "income");
    public static final Prefix PREFIX_INCOME_CAPS = new Prefix("I/", "income");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/", "address");
    public static final Prefix PREFIX_ADDRESS_CAPS = new Prefix("A/", "address");

    public static final Prefix PREFIX_EMAIL = new Prefix("e/", "email");
    public static final Prefix PREFIX_EMAIL_CAPS = new Prefix("E/", "email");
    public static final Prefix PREFIX_FAMILY = new Prefix("f/", "family");
    public static final Prefix PREFIX_FAMILY_CAPS = new Prefix("F/", "family");

    public static final Prefix PREFIX_TAG = new Prefix("t/", "tag");
    public static final Prefix PREFIX_TAG_CAPS = new Prefix("T/", "tag");
    public static final Prefix PREFIX_REMARK = new Prefix("r/", "remark");
    public static final Prefix PREFIX_REMARK_CAPS = new Prefix("R/", "remark");
}

