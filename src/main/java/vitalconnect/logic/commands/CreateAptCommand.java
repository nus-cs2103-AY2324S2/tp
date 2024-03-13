package vitalconnect.logic.commands;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;

/**
 * Represents a command to create an appointment for a person in the address book.
 * This command allows users to schedule appointments by specifying a patient's name
 * and the appointment's date and time.
 */
public class CreateAptCommand extends Command {

    public static final String COMMAND_WORD = "createApt";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates an appointment for a person in the address book. "
            + "Parameters: NAME /time DATE TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + "John Doe /time 02/02/2024 1330\n"
            + "Note: Ensure the date and time are in DD/MM/YYYY HHMM format.";

    private final String patientName;
    private final String dateTimeStr;

    /**
     * Constructs a {@code CreateAptCommand} to create an appointment.
     *
     * @param patientName The name of the patient for whom the appointment is being created.
     * @param dateTimeStr The date and time of the appointment, in DD/MM/YYYY HHMM format.
     */
    public CreateAptCommand(String patientName, String dateTimeStr) {
        this.patientName = patientName;
        this.dateTimeStr = dateTimeStr;
    }


    /**
     * Executes the command to create an appointment in the address book.
     *
     * The method checks if a person with the specified name exists in the address book.
     * If the person exists, it then attempts to parse the provided date and time string
     * to schedule the appointment. If the person does not exist or if the date and time
     * are in an incorrect format, a {@code CommandException} is thrown.
     *
     * @param model The model of the address book in which the appointment is to be created.
     * @return A {@code CommandResult} object containing the success message upon
     *         successful creation of the appointment.
     * @throws CommandException If the specified patient does not exist or if the
     *                          date and time string is in an incorrect format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            // Check if a person with the exact name exists
            if (!model.doesPersonExist(patientName)) {
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
            throw new CommandException("OOPS! The appointment cannot be created "
                    + "as the time is not in the correct format.");
        }
    }
}
