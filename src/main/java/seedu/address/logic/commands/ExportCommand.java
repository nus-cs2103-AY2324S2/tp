package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.storage.ExportManager;

/**
 * Exports the current list to CSV.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports the current viewed list to a CSV file. ";

    public static final String MESSAGE_SUCCESS = "List successfully exported and can be found in the exports folder.";

    public static final String MESSAGE_FAILURE = "Unable to export list.";

    private ObservableList<Person> personList;
    private ExportManager exportManager;

    /**
     * Creates an ExportCommand with the specified exportManager
     */
    public ExportCommand(ExportManager exportManager) {
        this.exportManager = exportManager;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ObservableList<Person> personList = model.getFilteredPersonList();
        this.personList = personList;
        try {
            this.exportManager.exportStudentList(personList);
        } catch (IOException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return personList.equals(otherExportCommand.personList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Student list to export: ", personList)
                .toString();
    }


}
