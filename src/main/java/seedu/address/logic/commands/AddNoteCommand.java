package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.IdentityCardNumberMatchesPredicate;
import seedu.address.model.person.Person;

/**
 * Adds a note to the person in the address book whose IC matches the argument IC.
 * Keyword matching is case-insensitive.
 */
public class AddNoteCommand extends Command {

    public static final String COMMAND_WORD = "addnote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a note to the person identified by their IC (case-insensitive).\n"
            + "Parameters: "
            + PREFIX_IC + "IC "
            + PREFIX_NOTE + "NOTE "
            + "Example: " + COMMAND_WORD
            + PREFIX_IC + " S0123456Q"
            + PREFIX_NOTE + " Healthy";

    public static final String MESSAGE_SUCCESS = "New note added to person : %1$s";

    private final IdentityCardNumberMatchesPredicate icNumber;

    public AddNoteCommand(IdentityCardNumberMatchesPredicate icNumber) {
        this.icNumber = icNumber;
    }
}
