package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

/**
 * Sorts all persons in the address book by an attribute of persons.
 */
public class SortCommand extends PersonCommand {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SUCCESS = "sorted all persons by name";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": sorts people according to an attribute and displays the sorted person list.\n"
            + "Parameters: PREFIX (corresponds to each person's attribute e.g. /n for Name)\n"
            + "Example: " + COMMAND_WORD + " n/";

    private final String prefix;

    /**
     * @param prefix referring to an attribute of persons to sort by
     */
    public SortCommand(String prefix) {
        requireNonNull(prefix);
        this.prefix = prefix;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortAddressBook(prefix);

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
