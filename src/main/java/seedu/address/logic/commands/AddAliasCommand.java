package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class AddAliasCommand extends Command {

    public static final String COMMAND_WORD = "alias";

    public static final String MESSAGE_SUCCESS = "New Alias added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This Alias already exists";

    private final String alias;
    private final String toReplace;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAliasCommand(String alias, String toReplace) {
        this.alias = alias;
        this.toReplace = toReplace;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.addAlias(alias, toReplace);
        return new CommandResult(String.format(MESSAGE_SUCCESS, alias));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddAliasCommand)) {
            return false;
        }

        AddAliasCommand otherAddAliasCommand = (AddAliasCommand) other;
        return alias.equals(otherAddAliasCommand.alias)
                && toReplace.equals(otherAddAliasCommand.toReplace);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("alias", alias)
                .add("toReplace", toReplace)
                .toString();
    }
}
