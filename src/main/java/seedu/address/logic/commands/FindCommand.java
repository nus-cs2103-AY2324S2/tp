package seedu.address.logic.commands;

/**
 * Finds and lists all persons in Tether whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public abstract class FindCommand extends Command {

    public static final String MESSAGE_USAGE = "find_[field]: Finds all persons whose names/phone/email contain "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Examples: " + "find_name alice bob charlie \n find_phone 123 456 789 \n find_email alice@gmail.com";

    public FindCommand() {
    }

}
