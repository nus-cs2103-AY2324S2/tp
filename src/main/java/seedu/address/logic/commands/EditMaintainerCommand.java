package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Commission;
import seedu.address.model.person.Email;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Skill;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing maintainer in the address book.
 */
public class EditMaintainerCommand extends Command {
    public static final String COMMAND_WORD = "/edit-maintenance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the maintainer identified "
            + "by the name used in the displayed person list.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_FIELD + "FIELD] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_SKILL + "SKILL] "
            + "[" + PREFIX_COMMISSION + "COMMISSION] "
            + "Example: " + COMMAND_WORD
            + PREFIX_NAME + "John Doe Others "
            + PREFIX_FIELD + "{ "
            + PREFIX_NAME + "John Deed "
            + PREFIX_ADDRESS + "NUS College Avenue"
            + " }";

    public static final String MESSAGE_EDIT_MAINTAINER_SUCCESS = "Edited Maintainer: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_MAINTAINER =
            "This maintainer's name already exists in the address book.";

    private final Name name;
    private final EditMaintainerDescriptor editMaintainerDescriptor;

    /**
     * @param name of the maintainer in the filtered person list to edit
     * @param editMaintainerDescriptor details to edit the maintainer with
     */
    public EditMaintainerCommand(Name name, EditMaintainerDescriptor editMaintainerDescriptor) {
        requireNonNull(name);
        requireNonNull(editMaintainerDescriptor);

        this.name = name;
        this.editMaintainerDescriptor = new EditMaintainerDescriptor(editMaintainerDescriptor);
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Maintainer maintainerToEdit = model.findMaintainerByName(name);

        if (maintainerToEdit == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_NAME);
        }

        Maintainer editedMantainer = createEditedMaintainer(maintainerToEdit, editMaintainerDescriptor);

        if (!maintainerToEdit.isSamePerson(editedMantainer) && model.hasPerson(editedMantainer)) {
            throw new CommandException(MESSAGE_DUPLICATE_MAINTAINER);
        }

        model.setPerson(maintainerToEdit, editedMantainer);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_MAINTAINER_SUCCESS, Messages.format(editedMantainer)));
    }

    /**
     * Creates and returns a {@code Maintainer} with the details of {@code maintainerToEdit}
     * edited with {@code editMaintainerDescriptor}.
     */
    private static Maintainer createEditedMaintainer(Maintainer maintainerToEdit,
            EditMaintainerDescriptor editMaintainerDescriptor) {
        assert maintainerToEdit != null;

        Name updatedName = editMaintainerDescriptor.getName().orElse(maintainerToEdit.getName());
        Phone updatedPhone = editMaintainerDescriptor.getPhone().orElse(maintainerToEdit.getPhone());
        Email updatedEmail = editMaintainerDescriptor.getEmail().orElse(maintainerToEdit.getEmail());
        Address updatedAddress = editMaintainerDescriptor.getAddress().orElse(maintainerToEdit.getAddress());
        Set<Tag> updatedTags = editMaintainerDescriptor.getTags().orElse(maintainerToEdit.getTags());
        Skill updatedSkill = editMaintainerDescriptor.getSkill().orElse(maintainerToEdit.getSkill());
        Commission updatedCommission = editMaintainerDescriptor.getCommission()
                .orElse(maintainerToEdit.getCommission());

        return new Maintainer(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedTags, updatedSkill, updatedCommission);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditMaintainerCommand)) {
            return false;
        }

        EditMaintainerCommand otherEditMaintainerCommand = (EditMaintainerCommand) other;
        return name.equals(otherEditMaintainerCommand.name)
                && editMaintainerDescriptor.equals(otherEditMaintainerCommand.editMaintainerDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("editMaintainerDescriptor", editMaintainerDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the maintainer with. Each non-empty field value will replace the
     * corresponding field value of the maintainer.
     */
    public static class EditMaintainerDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;
        private Skill skill;
        private Commission commission;

        public EditMaintainerDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditMaintainerDescriptor(EditMaintainerDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setSkill(toCopy.skill);
            setCommission(toCopy.commission);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, tags, skill, commission);
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

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSkill(Skill skill) {
            this.skill = skill;
        }

        public Optional<Skill> getSkill() {
            return Optional.ofNullable(skill);
        }

        public void setCommission(Commission commission) {
            this.commission = commission;
        }

        public Optional<Commission> getCommission() {
            return Optional.ofNullable(commission);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }


        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditMaintainerDescriptor)) {
                return false;
            }

            EditMaintainerDescriptor otherEditMaintainerDescriptor = (EditMaintainerDescriptor) other;
            boolean arePhoneEqual = Objects.equals(phone, otherEditMaintainerDescriptor.phone);
            boolean areEmailEqual = Objects.equals(email, otherEditMaintainerDescriptor.email);
            boolean areAddressEqual = Objects.equals(address, otherEditMaintainerDescriptor.address);
            boolean areTagsEqual = Objects.equals(tags, otherEditMaintainerDescriptor.tags);
            boolean areSkillEqual = Objects.equals(skill, otherEditMaintainerDescriptor.skill);
            boolean areCommissionEqual = Objects.equals(commission,
                    otherEditMaintainerDescriptor.commission);

            return arePhoneEqual && areEmailEqual && areAddressEqual
                    && areTagsEqual && areSkillEqual && areCommissionEqual;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .add("skill", skill)
                    .add("commission", commission)
                    .toString();
        }
    }
}
