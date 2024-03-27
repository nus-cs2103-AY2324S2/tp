package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.Email;
import seedu.address.model.person.Github;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Telegram;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "uc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Updates the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_CLASS_GROUP + "CLASS/GROUP] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_TELEGRAM + "TELEGRAM ID] "
            + "[" + PREFIX_GITHUB + "GITHUB ID]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Updated Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to update must be provided.";
    public static final String MESSAGE_NO_CHANGE = "The updated person is the same as the original person";
    public static final String MESSAGE_DUPLICATE_FIELD = "The updated person contains duplicate fields"
            + "(Email, Phone, Telegram, or Github) with another person.";

    private static final Logger logger = LogsCenter.getLogger(EditCommand.class);

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
        String message = "EditCommand created to update person at index: " + index.getOneBased()
                + ", with editPersonDescriptor: " + editPersonDescriptor;
        logger.log(Level.INFO, message);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);
        updatePerson(personToEdit, editedPerson, model);
        updateLastViewedPersonIfNecessary(personToEdit, editedPerson, model);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)));
    }

    private void updatePerson(Person personToEdit, Person editedPerson, Model model) throws CommandException {
        if (personToEdit.equals(editedPerson)) {
            throw new CommandException(MESSAGE_NO_CHANGE);
        }
        model.deletePerson(personToEdit);
        if (model.hasPerson(editedPerson)) {
            model.addPersonKeepFilter(personToEdit);
            throw new CommandException(MESSAGE_DUPLICATE_FIELD);
        }
        model.addPersonKeepFilter(editedPerson);
    }

    private void updateLastViewedPersonIfNecessary(Person personToEdit, Person editedPerson, Model model) {
        model.getLastViewedPerson()
                .filter(lastViewedPerson -> lastViewedPerson.equals(personToEdit))
                .ifPresent(lastViewedPerson -> model.updateLastViewedPerson(editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        ClassGroup updatedClassGroup = editPersonDescriptor.getClassGroup().orElse(personToEdit.getClassGroup());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Optional<Phone> updatedPhone = editPersonDescriptor.getPhone().isPresent()
                ? editPersonDescriptor.getPhone() : personToEdit.getPhone();
        Optional<Telegram> updatedTelegram = editPersonDescriptor.getTelegram().isPresent()
                ? editPersonDescriptor.getTelegram() : personToEdit.getTelegram();
        Optional<Github> updatedGithub = editPersonDescriptor.getGithub().isPresent()
                ? editPersonDescriptor.getGithub() : personToEdit.getGithub();
        return new Person(updatedName, updatedClassGroup, updatedEmail,
                updatedPhone, updatedTelegram, updatedGithub);
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
                && editPersonDescriptor.equals(otherEditCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private ClassGroup classGroup;
        private Email email;
        private Optional<Phone> phone;
        private Optional<Telegram> telegram;
        private Optional<Github> github;

        /**
         * Creates a new EditPersonDescriptor with empty fields.
         */
        public EditPersonDescriptor() {
            phone = Optional.empty();
            telegram = Optional.empty();
            github = Optional.empty();
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setClassGroup(toCopy.classGroup);
            setEmail(toCopy.email);
            setPhone(toCopy.phone);
            setTelegram(toCopy.telegram);
            setGithub(toCopy.github);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, email, classGroup)
                    || (phone.isPresent() || github.isPresent() || telegram.isPresent());
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setClassGroup(ClassGroup classGroup) {
            this.classGroup = classGroup;
        }

        public Optional<ClassGroup> getClassGroup() {
            return Optional.ofNullable(classGroup);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setPhone(Optional<Phone> phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return phone;
        }

        public void setTelegram(Optional<Telegram> telegram) {
            this.telegram = telegram;
        }

        public Optional<Telegram> getTelegram() {
            return telegram;
        }

        public void setGithub(Optional<Github> github) {
            this.github = github;
        }

        public Optional<Github> getGithub() {
            return github;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name)
                    && Objects.equals(classGroup, otherEditPersonDescriptor.classGroup)
                    && Objects.equals(email, otherEditPersonDescriptor.email)
                    && Objects.equals(phone, otherEditPersonDescriptor.phone)
                    && Objects.equals(telegram, otherEditPersonDescriptor.telegram)
                    && Objects.equals(github, otherEditPersonDescriptor.github);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("classGroup", classGroup)
                    .add("email", email)
                    .add("phone", phone)
                    .add("telegram", telegram)
                    .add("github", github)
                    .toString();
        }
    }
}
