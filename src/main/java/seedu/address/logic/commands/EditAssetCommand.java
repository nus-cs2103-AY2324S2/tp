package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.fields.Name.PREFIX_NAME;

import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.ArgumentMultimap;
import seedu.address.logic.util.ArgumentTokenizer;
import seedu.address.logic.util.ParserUtil;
import seedu.address.model.Model;
import seedu.address.model.asset.Asset;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.Address;
import seedu.address.model.person.fields.Assets;
import seedu.address.model.person.fields.Email;
import seedu.address.model.person.fields.Name;
import seedu.address.model.person.fields.Phone;
import seedu.address.model.person.fields.Tags;

/**
 * Edits the details of an asset in the address book.
 */
public class EditAssetCommand extends Command {

    public static final String COMMAND_WORD = "edita";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the asset identified "
            + "by the index number used in the displayed asset list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: NAME (must be an existing asset) "
            + "[NAME]"
            + "Example: " + COMMAND_WORD + " Aircon Hammer";

    public static final String MESSAGE_EDIT_ASSET_SUCCESS = "Edited Asset: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    private final Asset assetToEdit;
    private final EditAssetDescriptor editAssetDescriptor;

    /**
     * @param assetToEdit asset to edit
     * @param editAssetDescriptor details to edit the person with
     */
    public EditAssetCommand(Asset assetToEdit, EditAssetDescriptor editAssetDescriptor) {
        requireNonNull(assetToEdit);
        requireNonNull(editAssetDescriptor);

        this.assetToEdit = assetToEdit;
        this.editAssetDescriptor = new EditAssetDescriptor(editAssetDescriptor);
    }

    @Override
    public String execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (!model.hasAsset(assetToEdit)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ASSET_DISPLAYED);
        }

        for (Person p: lastShownList) {
            if (p.hasAsset(assetToEdit)) {
                Person editedPersonWithAsset = createEditedPersonWithAsset(assetToEdit, p, editAssetDescriptor);
                model.setPerson(p, editedPersonWithAsset);
            }
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return String.format(MESSAGE_EDIT_ASSET_SUCCESS, Messages.format(assetToEdit));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPersonWithAsset(Asset assetToEdit, Person personToEdit,
                                                      EditAssetDescriptor editAssetDescriptor) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Tags updatedTags = personToEdit.getTags();
        Assets updatedAssets = personToEdit.updateAsset(assetToEdit,
            Asset.of(editAssetDescriptor.getName().toString()));

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedTags, updatedAssets);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws IllegalArgumentException if the user input does not conform the expected format
     */
    public static EditAssetCommand of(String args) throws IllegalArgumentException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenizeEditAsset(args);

        Asset asset;

        try {
            asset = ParserUtil.parseAsset(argMultimap.getAssetToEdit());
        } catch (IllegalArgumentException ie) {
            throw new IllegalArgumentException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditAssetCommand.MESSAGE_USAGE), ie);
        }

        EditAssetDescriptor editAssetDescriptor = new EditAssetDescriptor();

        editAssetDescriptor.setName(Name.of(argMultimap.getValue(PREFIX_NAME).get()));

        if (!editAssetDescriptor.isAnyFieldEdited()) {
            throw new IllegalArgumentException(EditAssetCommand.MESSAGE_NOT_EDITED);
        }

        return new EditAssetCommand(asset, editAssetDescriptor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAssetCommand)) {
            return false;
        }

        EditAssetCommand otherEditCommand = (EditAssetCommand) other;
        return assetToEdit.equals(otherEditCommand.assetToEdit)
                && editAssetDescriptor.equals(otherEditCommand.editAssetDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("assetToEdit", assetToEdit)
                .add("editAssetDescriptor", editAssetDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the asset with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditAssetDescriptor {
        private Name name;

        public EditAssetDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditAssetDescriptor(EditAssetDescriptor toCopy) {
            setName(toCopy.name);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Name getName() {
            return name;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditAssetDescriptor)) {
                return false;
            }

            EditAssetDescriptor otherEditPersonDescriptor = (EditAssetDescriptor) other;
            return Objects.equals(name, otherEditPersonDescriptor.name);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .toString();
        }
    }
}
