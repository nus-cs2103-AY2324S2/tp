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

    public static final String[] ALLOWED_PREAMBLES = new String[] {"client", "housekeeper"};

    /**
     * Checks if a given command uses a preamble that is allowed. (we define preamble as TYPE)
     * @param preamble
     * @return true if the preamble is allowed and no if it is not.
     */
    public static boolean preambleIsAllowed(String preamble) {
        for (String s : ALLOWED_PREAMBLES) {
            if (s.equals(preamble)) {
                return true;
            }
        }
        return false;
    }
}
