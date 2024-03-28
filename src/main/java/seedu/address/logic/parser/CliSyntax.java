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
    public static final Prefix PREFIX_MATRIC_NUMBER = new Prefix("m/");

    public static final Prefix PREFIX_REFLECTION = new Prefix("r/");
    public static final Prefix PREFIX_STUDIO = new Prefix("s/");

    public static final Prefix PREFIX_IMPORT = new Prefix("i/");

    public static final Prefix PREFIX_SCORE = new Prefix("s/");
    public static final Prefix PREFIX_LESSTHAN = new Prefix("lt/");
    public static final Prefix PREFIX_MORETHAN = new Prefix("mt/");
}
