package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUNDING_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STARTUPS;

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
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;

/**
 * Edits the details of an existing startup in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the startup identified "
            + "by the index number used in the displayed startup list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_INDUSTRY + "INDUSTRY" + "] "
            + "[" + PREFIX_FUNDING_STAGE + "FUNDING STAGE] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_STARTUP_SUCCESS = "Edited Startup: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STARTUP = "This startup already exists in the address book.";

    private final Index index;
    private final EditStartupDescriptor editStartupDescriptor;

    /**
     * @param index of the startup in the filtered startup list to edit
     * @param editStartupDescriptor details to edit the startup with
     */
    public EditCommand(Index index, EditStartupDescriptor editStartupDescriptor) {
        requireNonNull(index);
        requireNonNull(editStartupDescriptor);

        this.index = index;
        this.editStartupDescriptor = new EditStartupDescriptor(editStartupDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Startup> lastShownList = model.getFilteredStartupList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STARTUP_DISPLAYED_INDEX);
        }

        Startup startupToEdit = lastShownList.get(index.getZeroBased());
        Startup editedStartup = createEditedStartup(startupToEdit, editStartupDescriptor);

        if (!startupToEdit.isSameStartup(editedStartup) && model.hasStartup(editedStartup)) {
            throw new CommandException(MESSAGE_DUPLICATE_STARTUP);
        }

        model.setStartup(startupToEdit, editedStartup);
        model.updateFilteredStartupList(PREDICATE_SHOW_ALL_STARTUPS);
        return new CommandResult(String.format(MESSAGE_EDIT_STARTUP_SUCCESS, Messages.format(editedStartup)));
    }

    /**
     * Creates and returns a {@code Startup} with the details of {@code startupToEdit}
     * edited with {@code editStartupDescriptor}.
     */
    private static Startup createEditedStartup(Startup startupToEdit, EditStartupDescriptor editStartupDescriptor) {
        assert startupToEdit != null;

        Name updatedName = editStartupDescriptor.getName().orElse(startupToEdit.getName());
        Phone updatedPhone = editStartupDescriptor.getPhone().orElse(startupToEdit.getPhone());
        FundingStage updatedFundingStage = editStartupDescriptor.getFundingStage().orElse(
            startupToEdit.getFundingStage());
        Industry updatedIndustry = editStartupDescriptor.getIndustry().orElse(startupToEdit.getIndustry());
        Email updatedEmail = editStartupDescriptor.getEmail().orElse(startupToEdit.getEmail());
        Address updatedAddress = editStartupDescriptor.getAddress().orElse(startupToEdit.getAddress());
        Set<Tag> updatedTags = editStartupDescriptor.getTags().orElse(startupToEdit.getTags());

        return new Startup(updatedName, updatedFundingStage, updatedIndustry,
            updatedPhone, updatedEmail, updatedAddress, updatedTags);
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
                && editStartupDescriptor.equals(otherEditCommand.editStartupDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editStartupDescriptor", editStartupDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the startup with. Each non-empty field value will replace the
     * corresponding field value of the startup.
     */
    public static class EditStartupDescriptor {
        private Name name;

        private Industry industry;

        private FundingStage fundingStage;

        private Phone phone;
        private Email email;
        private Address address;
        private Set<Tag> tags;

        public EditStartupDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStartupDescriptor(EditStartupDescriptor toCopy) {
            setName(toCopy.name);
            setFundingStage(toCopy.fundingStage);
            setIndustry(toCopy.industry);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, industry, fundingStage, phone, email, address, tags);
        }

        public void setIndustry(Industry industry) {
            this.industry = industry;
        }

        public void setFundingStage(FundingStage fundingStage) {
            this.fundingStage = fundingStage;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<Industry> getIndustry() {
            return Optional.ofNullable(industry);
        }

        public Optional<FundingStage> getFundingStage() {
            return Optional.ofNullable(fundingStage);
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
            if (!(other instanceof EditStartupDescriptor)) {
                return false;
            }

            EditStartupDescriptor otherEditStartupDescriptor = (EditStartupDescriptor) other;
            return Objects.equals(name, otherEditStartupDescriptor.name)
                    && Objects.equals(phone, otherEditStartupDescriptor.phone)
                    && Objects.equals(fundingStage, otherEditStartupDescriptor.fundingStage)
                    && Objects.equals(industry, otherEditStartupDescriptor.industry)
                    && Objects.equals(email, otherEditStartupDescriptor.email)
                    && Objects.equals(address, otherEditStartupDescriptor.address)
                    && Objects.equals(tags, otherEditStartupDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("industry", industry)
                    .add("funding stage", fundingStage)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("tags", tags)
                    .toString();
        }
    }
}
