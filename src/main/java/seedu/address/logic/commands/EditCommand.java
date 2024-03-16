package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.skill.Skill;

/**
 * Edits the details of an existing courseMate in the contact list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the courseMate identified "
            + "by the index number used in the displayed courseMate list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SKILL + "SKILL]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_COURSE_MATE_SUCCESS = "Edited CourseMate";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "This courseMate already exists in the contact list.";

    private final Index index;
    private final EditCourseMateDescriptor editCourseMateDescriptor;

    /**
     * @param index of the courseMate in the filtered courseMate list to edit
     * @param editCourseMateDescriptor details to edit the courseMate with
     */
    public EditCommand(Index index, EditCourseMateDescriptor editCourseMateDescriptor) {
        requireNonNull(index);
        requireNonNull(editCourseMateDescriptor);

        this.index = index;
        this.editCourseMateDescriptor = new EditCourseMateDescriptor(editCourseMateDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> lastShownList = model.getFilteredCourseMateList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        }

        CourseMate courseMateToEdit = lastShownList.get(index.getZeroBased());
        CourseMate editedCourseMate = createEditedCourseMate(courseMateToEdit, editCourseMateDescriptor);

        if (!courseMateToEdit.isSameCourseMate(editedCourseMate) && model.hasCourseMate(editedCourseMate)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_MATE);
        }

        model.setCourseMate(courseMateToEdit, editedCourseMate);
        model.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
        model.setRecentlyProcessedCourseMate(editedCourseMate);
        return new CommandResult(MESSAGE_EDIT_COURSE_MATE_SUCCESS, false, false, true);
    }

    /**
     * Creates and returns a {@code CourseMate} with the details of {@code courseMateToEdit}
     * edited with {@code editCourseMateDescriptor}.
     */
    private static CourseMate createEditedCourseMate(CourseMate courseMateToEdit,
                                                     EditCourseMateDescriptor editCourseMateDescriptor) {
        assert courseMateToEdit != null;

        Name updatedName = editCourseMateDescriptor.getName().orElse(courseMateToEdit.getName());
        Phone updatedPhone = editCourseMateDescriptor.getPhone().orElse(courseMateToEdit.getPhone());
        Email updatedEmail = editCourseMateDescriptor.getEmail().orElse(courseMateToEdit.getEmail());
        Set<Skill> updatedSkills = editCourseMateDescriptor.getSkills().orElse(courseMateToEdit.getSkills());

        return new CourseMate(updatedName, updatedPhone, updatedEmail, updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editCourseMateDescriptor.equals(otherEditCommand.editCourseMateDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editCourseMateDescriptor", editCourseMateDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the courseMate with. Each non-empty field value will replace the
     * corresponding field value of the courseMate.
     */
    public static class EditCourseMateDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Set<Skill> skills;

        public EditCourseMateDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditCourseMateDescriptor(EditCourseMateDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, skills);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        /**
         * Sets {@code skills} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCourseMateDescriptor)) {
                return false;
            }

            EditCourseMateDescriptor otherEditCourseMateDescriptor = (EditCourseMateDescriptor) other;
            return Objects.equals(name, otherEditCourseMateDescriptor.name)
                    && Objects.equals(phone, otherEditCourseMateDescriptor.phone)
                    && Objects.equals(email, otherEditCourseMateDescriptor.email)
                    && Objects.equals(skills, otherEditCourseMateDescriptor.skills);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("skills", skills)
                    .toString();
        }
    }
}
