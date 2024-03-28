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
    public static final Prefix PREFIX_TIMESLOT = new Prefix("t/");
    public static final Prefix PREFIX_GRADE = new Prefix("g/");

    /* Prefix definitions for edits */
    public static final Prefix OPTION_PRINT_NAME = new Prefix("!n");
    public static final Prefix OPTION_PRINT_PHONE = new Prefix("!p");
    public static final Prefix OPTION_PRINT_EMAIL = new Prefix("!e");
    public static final Prefix OPTION_PRINT_ADDRESS = new Prefix("!a");
    public static final Prefix OPTION_PRINT_TIMESLOT = new Prefix("!t");
    public static final Prefix OPTION_PRINT_GRADE = new Prefix("!g");
    public static final Prefix PREFIX_NAME_EDIT = new Prefix("name:");
    public static final Prefix PREFIX_PHONE_EDIT = new Prefix("phone:");
    public static final Prefix PREFIX_EMAIL_EDIT = new Prefix("email:");
    public static final Prefix PREFIX_ADDRESS_EDIT = new Prefix("address:");
    public static final Prefix PREFIX_TIMESLOT_EDIT = new Prefix("timeslot:");
    public static final Prefix PREFIX_GRADE_EDIT = new Prefix("grade:");
}
