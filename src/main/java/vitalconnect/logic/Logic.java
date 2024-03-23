package vitalconnect.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import vitalconnect.commons.core.GuiSettings;
import vitalconnect.logic.commands.CommandResult;
import vitalconnect.logic.commands.exceptions.CommandException;
import vitalconnect.logic.parser.exceptions.ParseException;
import vitalconnect.model.Appointment;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.person.Person;


/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the Clinic.
     *
     * @see vitalconnect.model.Model#getClinic()
     */
    ReadOnlyClinic getClinic();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the filtered list of appointment */
    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the user prefs' clinic file path.
     */
    Path getClinicFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
