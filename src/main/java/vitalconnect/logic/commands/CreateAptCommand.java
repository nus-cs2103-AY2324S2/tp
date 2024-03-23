package vitalconnect.logic.commands;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.model.Appointment;
import vitalconnect.model.Model;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;


/**
 * Represents a command to create an appointment for a patient in the address book.
 * This command schedules appointments by specifying the patient's NRIC
 * and the desired date and time for the appointment.
 */
public class CreateAptCommand extends Command {

    public static final String COMMAND_WORD = "adda";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds an appointment for a person in the address book. "
            + "Parameters: NRIC /time DATE TIME\n"
            + "Example: " + COMMAND_WORD + " "
            + "S1234567D /time 02/02/2024 1330\n"
            + "Note: Ensure the date and time are in DD/MM/YYYY HHMM format.";

    private final String patientIc;
    private final String dateTimeStr;
    private String patientName = null;

    /**
     * Constructs a {@code CreateAptCommand} to schedule an appointment.
     *
     * @param patientIc The NRIC of the patient for whom the appointment is being created.
     * @param dateTimeStr The date and time of the appointment, in DD/MM/YYYY HHMM format.
     */
    public CreateAptCommand(String patientIc, String dateTimeStr) {
        this.patientIc = patientIc;
        this.dateTimeStr = dateTimeStr;
    }


    /**
     * Executes the command to create an appointment in the address book.
     *
     * The method verifies the existence of a person with the specified NRIC in the address book.
     * If the person exists and the date and time format is valid, an appointment is scheduled.
     * If the person does not exist or the date and time are incorrectly formatted,
     * a {@code CommandException} is thrown.
     *
     * @param model The model of the address book in which the appointment is to be created.
     * @return A {@code CommandResult} object containing the success message upon
     *         successful creation of the appointment, and the type of command result.
     * @throws CommandException If the specified patient does not exist or if the
     *                          date and time string is in an incorrect format.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            // Check if a person with the exact name exists
            if (!model.doesIcExist(patientIc)) {
                throw new CommandException("OOPS! The appointment cannot be created as the NRIC does not exist.");
            }
            // Parse and validate date time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            Nric nric = new Nric(patientIc);
            Person person = model.findPersonByNric(nric);
            Name name = person.getIdentificationInformation().getName();
            this.patientName = name.toString();
            Appointment appointment = new Appointment(patientName, patientIc, dateTime);
            model.addAppointment(appointment);

            return new CommandResult(String.format("Created an appointment successfully!\nName: %s\nNRIC: %s\nTime: %s",
                    patientName, patientIc, dateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"))),
                    false, false, CommandResult.Type.SHOW_APPOINTMENTS);

        } catch (DateTimeParseException e) {
            throw new CommandException("OOPS! The appointment cannot be created "
                    + "as the time is empty or not in the correct format.");
        }
    }

    /**
     * Returns the NRIC of the patient associated with this appointment.
     *
     * @return The patient's NRIC as a {@code String}.
     */
    public String getPatientIc() {
        return patientIc;
    }

    /**
     * Returns the name of the patient associated with this appointment.
     *
     * @return The patient's name as a {@code String}.
     */
    public String getPatientName() {
        return patientName;
    }
    /**
     * Returns the date and time of the appointment as a string.
     *
     * @return The date and time of the appointment in "dd/MM/yyyy HHmm" format.
     */
    public String getDateTimeStr() {
        return dateTimeStr;
    }
}
