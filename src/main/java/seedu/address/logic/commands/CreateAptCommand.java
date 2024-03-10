package seedu.address.logic.commands;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.Appointment;

public class CreateAptCommand extends Command {
    private final String patientName;
    private final String dateTimeStr;
    public static final String COMMAND_WORD = "createApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an appointment for a person in the address book. "
            + "Parameters: NAME /time DATE TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + "John Doe /time 02/02/2024 1330\n"
            + "Note: Ensure the date and time are in DD/MM/YYYY HHMM format.";

    public CreateAptCommand(String patientName, String dateTimeStr) {
        this.patientName = patientName;
        this.dateTimeStr = dateTimeStr;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            // Check if a person with the exact name exists
            if (!model.personExist(patientName)) {
                throw new CommandException("OOPS! The appointment cannot be created as the patient does not exist.");
            }

            // Parse and validate date time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);


            Appointment appointment = new Appointment(patientName, dateTime);
            model.addAppointment(appointment);

            return new CommandResult(String.format("Created an appointment successfully!\nName: %s\nTime: %s",
                    patientName, dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))));


        } catch (DateTimeParseException e) {
            throw new CommandException("OOPS! The appointment cannot be created as the time is not in the correct format.");
        }
    }

}
