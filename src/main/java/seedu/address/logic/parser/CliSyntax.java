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
    public static final Prefix PREFIX_MEETING = new Prefix("m/");
    public static final Prefix PREFIX_POLICY_NAME = new Prefix("pol/");
    public static final Prefix PREFIX_POLICY_NUMBER = new Prefix("polnum/");

    public static final Prefix PREFIX_PREMIUM_TERM = new Prefix("pterm/");
    public static final Prefix PREFIX_PREMIUM = new Prefix("prem/");
    public static final Prefix PREFIX_BENEFIT = new Prefix("b/");

}
