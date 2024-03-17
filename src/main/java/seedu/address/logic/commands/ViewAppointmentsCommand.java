package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows all appointments in the filtered address book.
 */
public class ViewAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "appointments";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all appointments with everyone in the address book.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        // Get all appointments from the last shown list of persons
        List<String> appointments = lastShownList.stream()
                .flatMap(person -> person.getAppointments().stream())
                .map(Object::toString)
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        sb.append("Appointments:\n");
        for (String appointment : appointments) {
            sb.append(appointment).append("\n");
        }

        return new CommandResult(sb.toString().trim());
    }
}
