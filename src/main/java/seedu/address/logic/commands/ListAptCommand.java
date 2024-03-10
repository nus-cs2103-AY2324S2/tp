package seedu.address.logic.commands;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Appointment;
import seedu.address.model.Model;
import static java.util.Objects.requireNonNull;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


public class ListAptCommand extends Command {

    public static final String COMMAND_WORD = "listApt";
    public static final String MESSAGE_SUCCESS = "Listed all appointments";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        StringBuilder result = new StringBuilder(MESSAGE_SUCCESS + ":\n");
        AtomicInteger index = new AtomicInteger(1); // For indexing appointments

        String appointmentsList = model.getFilteredAppointmentList().stream()
                .sorted(Comparator.comparing(Appointment::getDateTime)) // Sort by datetime
                .map(appointment -> index.getAndIncrement() + ". " + appointment.toString())
                .collect(Collectors.joining("\n"));

        result.append(appointmentsList);

        return new CommandResult(result.toString().trim());
    }
}