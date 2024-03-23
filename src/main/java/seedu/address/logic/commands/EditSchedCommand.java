package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_SCHEDULES;

import java.time.LocalDateTime;
import java.util.*;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.*;
import seedu.address.model.schedule.Schedule;

/**
 * Adds a schedule to the address book.
 */
public class EditSchedCommand extends Command {

    public static final String COMMAND_WORD = "editSched";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edit a schedule in address book. "
            + "Parameters: "
            + "TASK INDEX(S) (must be positive integer) "
            + PREFIX_SCHEDULE + " TO DELETE PERSON "
            + "[" + PREFIX_SCHEDULE + " SCHEDULE] "
            + "[" + PREFIX_START + " START DATETIME (yyyy-MM-dd HH:mm)] "
            + "[" + PREFIX_END + " END DATETIME (yyyy-MM-dd HH:mm)] "
            + "Example: " + COMMAND_WORD + " " + "1"
            + "[" + PREFIX_SCHEDULE + " CS2103 weekly meeting] "
            + "[" + PREFIX_START + " 2024-02-24 15:00] "
            + "[" + PREFIX_END + " 2024-02-24 17:00] ";

    public static final String MESSAGE_EDIT_SCHEDULE_SUCCESS = "Edited Schedule: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_SCHEDULE =
            "This schedule already exists in the address book.";

    private final Index targetIndex;

    private final EditSchedCommand.EditScheduleDescriptor editScheduleDescriptor;


    /**
     * Creates EditSchedCommand object
     *
     * @param targetIndex index of schedule to edit
     * @param editScheduleDescriptor to create edited schedule
     */
    public EditSchedCommand(Index targetIndex,
                            EditSchedCommand.EditScheduleDescriptor editScheduleDescriptor) {
        requireNonNull(targetIndex);
        requireNonNull(editScheduleDescriptor);

        this.targetIndex = targetIndex;
        this.editScheduleDescriptor =
                new EditSchedCommand.EditScheduleDescriptor(editScheduleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Schedule> lastShownList = model.getFilteredScheduleList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Schedule scheduleToEdit = lastShownList.get(targetIndex.getZeroBased());
        Schedule editedSchedule = createEditedSchedule(model, scheduleToEdit, editScheduleDescriptor);

        if (!scheduleToEdit.isSameSchedule(editedSchedule) && model.hasSchedule(editedSchedule)) {
            throw new CommandException(MESSAGE_DUPLICATE_SCHEDULE);
        }

        model.deleteSchedule(scheduleToEdit, scheduleToEdit.getPersonList());
        model.addSchedule(editedSchedule, editedSchedule.getPersonList());
        model.updateFilteredScheduleList(PREDICATE_SHOW_ALL_SCHEDULES);
        return new CommandResult(String.format(MESSAGE_EDIT_SCHEDULE_SUCCESS,
                Messages.format(editedSchedule)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Schedule createEditedSchedule(Model model, Schedule scheduleToEdit,
                                                 EditSchedCommand.EditScheduleDescriptor editScheduleDescriptor) {
        assert scheduleToEdit != null;

        String updatedSchedName = editScheduleDescriptor.getSchedName().orElse(scheduleToEdit.getSchedName());
        LocalDateTime updatedStartTime = editScheduleDescriptor.getStartTime().orElse(scheduleToEdit.getStartTime());
        LocalDateTime updatedEndTime = editScheduleDescriptor.getEndTime().orElse(scheduleToEdit.getEndTime());
        ArrayList<Person> updatedPersonList;
        ArrayList<NameContainsKeywordsPredicate> personNamePredicateList;
        if (editScheduleDescriptor.getPersonList().isEmpty()) {
            updatedPersonList = scheduleToEdit.getPersonList();
        } else {
            updatedPersonList = new ArrayList<Person>();
            for (NameContainsKeywordsPredicate predicate: editScheduleDescriptor.getPersonList().get()) {
                ObservableList<Person> filteredPersonList = model.getFilteredPersonList();
                ObservableList<Person> newFilteredPersonList = filteredPersonList.filtered(predicate);
                updatedPersonList.add(newFilteredPersonList.get(0));
            }
        }
        return new Schedule(updatedSchedName, updatedStartTime, updatedEndTime, updatedPersonList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditSchedCommand)) {
            return false;
        }

        EditSchedCommand otherEditSchedCommand = (EditSchedCommand) other;
        return targetIndex.equals(otherEditSchedCommand.targetIndex)
                && editScheduleDescriptor.equals(otherEditSchedCommand.editScheduleDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .add("editScheduleDescriptor", editScheduleDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditScheduleDescriptor {
        private String schedName;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private ArrayList<NameContainsKeywordsPredicate> personNameList;

        public EditScheduleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditScheduleDescriptor(EditSchedCommand.EditScheduleDescriptor toCopy) {
            setSchedName(toCopy.schedName);
            setStartTime(toCopy.startTime);
            setEndTime(toCopy.endTime);
            setPersonList(toCopy.personNameList);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(schedName, startTime, endTime, personNameList);
        }

        public void setSchedName(String schedName) {
            this.schedName = schedName;
        }

        public Optional<String> getSchedName() {
            return Optional.ofNullable(schedName);
        }

        public void setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
        }

        public Optional<LocalDateTime> getStartTime() {
            return Optional.ofNullable(startTime);
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }

        public Optional<LocalDateTime> getEndTime() {
            return Optional.ofNullable(endTime);
        }

        public void setPersonList(ArrayList<NameContainsKeywordsPredicate> personNameList) {
            this.personNameList = personNameList;
        }

        public Optional<ArrayList<NameContainsKeywordsPredicate>> getPersonList() {
            return Optional.ofNullable(personNameList);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditSchedCommand.EditScheduleDescriptor)) {
                return false;
            }

            EditSchedCommand.EditScheduleDescriptor otherEditPersonDescriptor = (EditSchedCommand.EditScheduleDescriptor) other;
            return Objects.equals(schedName, otherEditPersonDescriptor.schedName)
                    && Objects.equals(startTime, otherEditPersonDescriptor.startTime)
                    && Objects.equals(endTime, otherEditPersonDescriptor.endTime)
                    && Objects.equals(personNameList, otherEditPersonDescriptor.personNameList);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", schedName)
                    .add("phone", startTime)
                    .add("email", endTime)
                    .add("address", personNameList)
                    .toString();
        }
    }
}
