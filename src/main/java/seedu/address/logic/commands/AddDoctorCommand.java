package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.person.Person;

import java.util.Date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Add a doctor into the database
 */
public class AddDoctorCommand extends Command {

    public static final String COMMAND_WORD = "adddoctor";
    public static final String MESSAGE_SUCCESS = "New doctor added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";
    private final Doctor toAdd;
    public AddDoctorCommand(Doctor doctor) {
        requireNonNull(doctor);
        this.toAdd = doctor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddDoctorCommand)) {
            return false;
        }

        // state check
        AddDoctorCommand e = (AddDoctorCommand) other;
        return toAdd.equals(e.toAdd);
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
