package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;


public class ViewCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View a client. \n"
            + "Parameters: \n"
            + "index - Index shown in the corresponding contact list\n"
            + "Example: " + COMMAND_WORD + " 2";

    private Index ClientIndex;

    public static final String MESSAGE_SUCCESS = "You are now viewing Client with index: " + ;

    public ViewCommand(Index ClientIndex) {
        this.ClientIndex = ClientIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        return null;
    }
}
