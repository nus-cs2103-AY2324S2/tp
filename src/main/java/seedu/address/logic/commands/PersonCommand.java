package seedu.address.logic.commands;

/**
 * Represents a command with functionality pertaining to persons.
 */
public abstract class PersonCommand extends Command {
    @Override
    public String getCommandType() {
        return "personCommand";
    };

}

