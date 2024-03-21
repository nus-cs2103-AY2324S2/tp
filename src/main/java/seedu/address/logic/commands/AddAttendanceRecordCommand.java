package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE_RECORD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.StudentId;
import seedu.address.model.tag.Attendance;

/**
 * Adds an attendance record to students.
 */
public class AddAttendanceRecordCommand extends Command {

    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add attendance record. "
            + "Parameters: "
            + PREFIX_ATTENDANCE_RECORD + "DATE\n"
            + "Example: " + COMMAND_WORD + " ar/ 19-03-2024";


    public static final String MESSAGE_NOT_IMPLEMENTED_YET = "attendance command not implemented yet";
    public static final String MESSAGE_SUCCESS = "New attendance added for: %1$s";
    public static final String MESSAGE_ARGUMENTS = "Date: %1$s";
    private Attendance date;

    /**
     * Creates an AddAttendanceRecord to add the specified {@code date}
     */
    public AddAttendanceRecordCommand(Attendance date) {
        requireAllNonNull(date);
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        if (lastShownList.size() == 0) {
            throw new CommandException(Messages.MESSAGE_NO_PERSON_IN_THE_CLASS);
        }
        for (Attendance i : lastShownList.get(0).getAttendances()) {
            if (date.attendanceName.getDate().equals(i.attendanceName.getDate())) {
                throw new CommandException(Messages.MESSAGE_DUPLICATE_DATE_ATTENDANCE);
            }
        }

        for (int i = 0; i < lastShownList.size(); i++) {
            Index index = Index.fromZeroBased(i);

            EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
            editPersonDescriptor.setName(lastShownList.get(i).getName());
            editPersonDescriptor.setPhone(lastShownList.get(i).getPhone());
            editPersonDescriptor.setEmail(lastShownList.get(i).getEmail());
            editPersonDescriptor.setStudentId(lastShownList.get(i).getStudentId());
            Set<Attendance> set = new HashSet<>(lastShownList.get(i).getAttendances());
            if (set == null) {
                set = new HashSet<>();
            }
            set.add(this.date);
            editPersonDescriptor.setAttendances(set);

            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

            if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
                throw new CommandException(MESSAGE_DUPLICATE_PERSON);
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }


        return new CommandResult(String.format(MESSAGE_SUCCESS, date));
    }

    private static Person createEditedPerson(Person personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        StudentId updatedStudentId = editPersonDescriptor.getStudentId().orElse(personToEdit.getStudentId());
        Set<Attendance> updatedAttendances = editPersonDescriptor.getTags().orElse(personToEdit.getAttendances());


        return new Person(updatedName, updatedPhone, updatedEmail, updatedStudentId, updatedAttendances);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddAttendanceRecordCommand)) {
            return false;
        }

        AddAttendanceRecordCommand otherAddAttendanceRecordCommand = (AddAttendanceRecordCommand) other;
        return date.equals(otherAddAttendanceRecordCommand.date);
    }
}
