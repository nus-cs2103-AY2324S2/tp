package seedu.address.logic.relationship;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

public class ListRelationshipTypesCommand extends Command {
    public static final String COMMAND_WORD = "listRelations";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String MESSAGE_SUCCESS = model.showRelationshipTypes();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
