package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

public class AttendanceCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "Indexes: %1$d, Date: %2$s";

    private final Set<Index> indexes;
    private final LocalDate date;

    public static final String COMMAND_WORD = "att";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the attendance date to the persons identified "
            + "by the index numbers used in the last person listing. \n"
            + "Parameters: INDEXES (must be positive integers separated by a whitespace) "
            + "d/ [DATE]\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + "d/ 2024-02-02";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Persons: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTENDANCE = "This attendance has already been marked for %1$s";

    public AttendanceCommand(Set<Index> indexes, LocalDate date) {
        requireAllNonNull(indexes, date);

        this.indexes = indexes;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        Set<Name> editedNames = new HashSet<>();
        for (Index index : this.indexes) {
            requireNonNull(model);
            List<Person> lastShownList = model.getFilteredPersonList();

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownList.get(index.getZeroBased());
            Attendance attendance = new Attendance(date);
            Person editedPerson = createEditedPerson(personToEdit, attendance);
            editedNames.add(editedPerson.getName());

            if (personToEdit.getAttendances().contains(attendance)) {
                throw new CommandException(String.format(MESSAGE_DUPLICATE_ATTENDANCE, personToEdit.getName()));
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedNames));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, Attendance attendance) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<Attendance> updatedAttendances = new HashSet<>(personToEdit.getAttendances());
        updatedAttendances.add(attendance);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedAttendances);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        AttendanceCommand e = (AttendanceCommand) other;
        return indexes.equals(e.indexes)
                && date.equals(e.date);
    }
}
