package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Patient> sortedList = new ArrayList<Patient>();
        sortedList.addAll(model.getFilteredPersonList());
        Comparator<Patient> comparator = (patient1, patient2) -> {
            return patient1.getName().fullName.compareTo(patient2.getName().fullName);
        };
        sortedList.sort(comparator);
        for(Patient patient : sortedList) {
            model.deletePerson(patient);
        }
        for(Patient patient : sortedList) {
            model.addPerson(patient);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
