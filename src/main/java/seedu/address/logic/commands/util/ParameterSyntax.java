package seedu.address.logic.commands.util;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSNET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

/**
 * Contains parameter syntax definitions and examples common to multiple commands.
 */
public class ParameterSyntax {

    /* Parameter definitions */
    public static final Parameter PARAMETER_NAME = new DefinedParameter(
            PREFIX_NAME,
            "NAME",
            "John Doe"
    );

    public static final Parameter PARAMETER_PHONE = new DefinedParameter(
            PREFIX_PHONE,
            "PHONE",
            "98765432"
    );

    public static final Parameter PARAMETER_EMAIL = new DefinedParameter(
            PREFIX_EMAIL,
            "EMAIL",
            "johndoe@example.com"
    );

    public static final Parameter PARAMETER_NUSNET = new DefinedParameter(
            PREFIX_NUSNET,
            "NUSNET",
            "e0123456"
    );

    public static final Parameter PARAMETER_ADDRESS = new DefinedParameter(
            PREFIX_ADDRESS,
            "ADDRESS",
            "311, Clementi Ave 2, #02-25"
    );

    public static final Parameter PARAMETER_TAG = new DefinedParameter(
            PREFIX_TAG,
            "TAG",
            "friends",
            "owesMoney"
    );

    public static final Parameter PARAMETER_INDEX = new Parameter(
            "INDEX",
            "must be a positive integer",
            "1"
    );
}
