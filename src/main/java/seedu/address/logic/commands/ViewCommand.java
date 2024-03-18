package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a client. \n"
            + "Parameters: \n"
            + "index - Index shown in the corresponding contact list\n"
            + "Example: " + COMMAND_WORD + " 2";

    private final Index clientIndex;

    public static final String MESSAGE_SUCCESS = "You are now viewing Client with index: ";


    public ViewCommand(Index clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (this.clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        Person selectedClient = lastShownList.get(this.clientIndex.getZeroBased());
        return new CommandResult(MESSAGE_SUCCESS + this.clientIndex.getOneBased());
    }
}
