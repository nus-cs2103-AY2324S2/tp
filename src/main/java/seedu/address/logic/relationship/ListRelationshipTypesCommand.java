package seedu.address.logic.relationship;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all the relationship types in the address book.
 */
public class ListRelationshipTypesCommand extends Command {
    public static final String COMMAND_WORD = "listRelations";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        String successMessage = model.showRelationshipTypes();
        return new CommandResult(successMessage);
    }
}
