package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.InternshipData;
import seedu.address.model.InternshipModel;


/**
 * Clears all internship data.
 */
public class InternshipClearCommand extends InternshipCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Internship data has been cleared!";


    @Override
    public CommandResult execute(InternshipModel model) {
        requireNonNull(model);
        model.setInternshipData(new InternshipData());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
