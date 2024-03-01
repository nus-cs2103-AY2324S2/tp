package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_NAME;


import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;

/**
 * Creates and adds an event to the address book.
 */
public class CreateEventCommand extends Command {

    public static final String COMMAND_WORD = "createEvent";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates and adds an event to the address book. "
            + "Parameters: "
            + PREFIX_EVENT_NAME + "EVENT_NAME";


    public static final String MESSAGE_SUCCESS = "New event created: %1$s";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the address book";

    private final Event toAdd;

    /**
     * Creates a CreateEventCommand to add the specified {@code Event}.
     */
    public CreateEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }

        model.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CreateEventCommand)) {
            return false;
        }

        CreateEventCommand otherCreateEventCommand = (CreateEventCommand) other;
        return toAdd.equals(otherCreateEventCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
