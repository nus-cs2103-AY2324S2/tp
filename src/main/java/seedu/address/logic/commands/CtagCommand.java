package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;


/**
 * Adds a tag to the address book.
 */
public class CtagCommand extends Command {

    public static final String COMMAND_WORD = "ctag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to the address book. "
            + "Parameters: "
            + "tagName\n"
            + "Example: " + COMMAND_WORD + " "
            + "FRIEND";

    public static final String MESSAGE_SUCCESS = "New tag added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This tag already exists in the address book";
    private final Tag toAdd;

    /**
     * Creates an CtagCommand to add the specified {@code Person}
     */
    public CtagCommand(Tag toAdd) {
        requireNonNull(toAdd);
        this.toAdd = toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasTag(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addTag(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CtagCommand)) {
            return false;
        }

        CtagCommand e = (CtagCommand) other;
        return toAdd.equals(e.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
