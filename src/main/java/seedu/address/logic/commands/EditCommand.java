package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.TelegramHandle;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.skill.Skill;

/**
 * Edits the details of an existing courseMate in the contact list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the courseMate identified "
            + "by the index number used in the displayed courseMate list. "
            + "Existing values will be overwritten by the input values.\n"
            + "NAME can be specified either by full name or by the '#' notation.\n"
            + "Parameters: NAME "
            + "[" + PREFIX_NAME + " NEW_NAME] "
            + "[" + PREFIX_PHONE + " NEW_PHONE_NUMBER] "
            + "[" + PREFIX_EMAIL + " NEW_EMAIL] "
            + "[" + PREFIX_TELEGRAM + " NEW_TELEGRAM_HANDLE]\n"
            + "Example: " + COMMAND_WORD + " John Doe "
            + PREFIX_PHONE + " 91234567 "
            + PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_COURSE_MATE_SUCCESS = "Edited CourseMate";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COURSE_MATE_NAME =
            "This courseMate already exists in the contact list.";

    private final QueryableCourseMate queryableCourseMate;
    private final EditCourseMateDescriptor editCourseMateDescriptor;


    /**
     * @param queryableCourseMate courseMate that we want to edit
     * @param editCourseMateDescriptor details to edit the courseMate with
     */
    public EditCommand(QueryableCourseMate queryableCourseMate, EditCourseMateDescriptor editCourseMateDescriptor) {
        requireNonNull(queryableCourseMate);
        requireNonNull(editCourseMateDescriptor);

        this.queryableCourseMate = queryableCourseMate;
        this.editCourseMateDescriptor = new EditCourseMateDescriptor(editCourseMateDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> courseMateToEditList;

        try {
            courseMateToEditList = model.findCourseMate(queryableCourseMate);
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        }

        //If there are more than 1 matching names
        if (courseMateToEditList.size() > 1) {
            return new SimilarNameCommand(queryableCourseMate).execute(model);
        }

        CourseMate courseMateToEdit = courseMateToEditList.get(0);
        CourseMate editedCourseMate = createEditedCourseMate(courseMateToEdit, editCourseMateDescriptor);

        if (!courseMateToEdit.isSameCourseMate(editedCourseMate) && model.hasCourseMate(editedCourseMate)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_MATE_NAME);
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
        TelegramHandle updatedTelegramHandle = editCourseMateDescriptor.getTelegramHandle()
                .orElse(courseMateToEdit.getTelegramHandle());
        Set<Skill> updatedSkills = courseMateToEdit.getSkills();

        return new CourseMate(updatedName, updatedPhone, updatedEmail, updatedTelegramHandle, updatedSkills);
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
        return queryableCourseMate.equals(otherEditCommand.queryableCourseMate)
                && editCourseMateDescriptor.equals(otherEditCommand.editCourseMateDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", queryableCourseMate.getIndex())
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
        private TelegramHandle telegramHandle;

        public EditCourseMateDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public EditCourseMateDescriptor(EditCourseMateDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setTelegramHandle(toCopy.telegramHandle);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, telegramHandle);
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

        public void setTelegramHandle(TelegramHandle telegramHandle) {
            this.telegramHandle = telegramHandle;
        }

        public Optional<TelegramHandle> getTelegramHandle() {
            return Optional.ofNullable(telegramHandle);
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
                    && Objects.equals(email, otherEditCourseMateDescriptor.email);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .toString();
        }
    }
}
