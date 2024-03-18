package seedu.address.logic.parser;

import seedu.address.model.person.PersonType;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final PersonType TYPE_STU = PersonType.STU;
    public static final PersonType TYPE_TA = PersonType.TA;
    public static final PersonType TYPE_DEFAULT = PersonType.STU;
    public static final Prefix PREFIX_NAME = new Prefix("/n");
    public static final Prefix PREFIX_ID = new Prefix("/i");
    public static final Prefix PREFIX_PHONE = new Prefix("/p");
    public static final Prefix PREFIX_EMAIL = new Prefix("/e");
    public static final Prefix PREFIX_ADDRESS = new Prefix("/a");
    public static final Prefix PREFIX_TAG = new Prefix("/t");

}
