package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class FindTagCommand extends Command {
    public static final String COMMAND_WORD = "findtag";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain the specified" +
            " text (case-insensitive) and displays them in the list.\n" +
            "Parameters: KEYWORD [TEXT]...”\n";
    private static boolean containsCaseInsensitive(String str, String subStr) {
        return str.toLowerCase().contains(subStr.toLowerCase());
    }

    private final String tagName;

    public FindTagCommand(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(person -> person
                .getTags()
                .stream()
                .anyMatch(tag -> containsCaseInsensitive(tag.tagName, tagName)));
        return new CommandResult
                (String.format("Found %d people with a tag that has '%s'", model.getFilteredPersonList().size(), tagName));

    }
}
