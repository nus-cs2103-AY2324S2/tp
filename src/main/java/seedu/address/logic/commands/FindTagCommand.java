package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds and lists all persons in address book that has a tag that contains the given text. Matching is
 * case insensitive.
 */
public class FindTagCommand extends Command {
    public static final String COMMAND_WORD = "findtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain the specified"
        + " text (case-insensitive) and displays them in the list.\n"
        + "Parameters: KEYWORD”\n"
        + "Example: " + COMMAND_WORD + " friend";
    public static final String MESSAGE_FOUND_PEOPLE = "Found %d people with a tag that has '%s'";

    private final String subString;

    /**
     * Creates a FindTagCommand to find persons with tags that contain {@code str}.
     * @param str the substring to search for. This instructs the command to find all persons with a tag that contain
     *            this {@code String}.
     */
    public FindTagCommand(String str) {
        requireNonNull(str);
        this.subString = str;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredPersonList(person -> subString.isEmpty()
                || person
                .getTags()
                .stream()
                .anyMatch(tag -> StringUtil.containsSubstringIgnoreCase(tag.tagName, subString))
        );
        return new CommandResult(String.format(MESSAGE_FOUND_PEOPLE,
                model.getFilteredPersonList().size(), subString));

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTagCommand)) {
            return false;
        }

        FindTagCommand otherFindTagCommand = (FindTagCommand) other;
        return subString.equals(otherFindTagCommand.subString);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("subString", subString)
                .toString();
    }
}
