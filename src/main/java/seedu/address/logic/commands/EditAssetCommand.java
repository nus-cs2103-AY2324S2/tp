package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.util.ArgumentMultimap;
import seedu.address.logic.util.ArgumentTokenizer;
import seedu.address.model.Model;
import seedu.address.model.asset.Asset;
import seedu.address.model.person.fields.Prefix;

/**
 * Edits the details of an asset in the address book.
 */
public class EditAssetCommand extends Command {

    public static final String COMMAND_WORD = "edita";
    public static final Prefix PREFIX_OLD = new Prefix("old/");
    public static final Prefix PREFIX_NEW = new Prefix("new/");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the asset identified "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: " + PREFIX_OLD + "OLDNAME " + PREFIX_NEW + "NEWNAME\n"
            + "OLDNAME must be an existing asset name"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OLD + "Aircon " + PREFIX_NEW + "Hammer";

    public static final String MESSAGE_SUCCESS = "Edited Asset: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_INVALID_ASSET_NAME = "The asset name provided is invalid";

    private final Asset target;
    private final Asset editedAsset;

    /**
     * @param target Previous asset to replace.
     * @param editedAsset New asset to replace with.
     */
    public EditAssetCommand(Asset target, Asset editedAsset) {
        requireNonNull(target);
        requireNonNull(editedAsset);

        this.target = target;
        this.editedAsset = editedAsset;
    }

    @Override
    public String execute(Model model) throws CommandException {
        requireNonNull(model);

        if (target.equals(editedAsset)) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }
        if (!model.hasAsset(target)) {
            throw new CommandException(MESSAGE_INVALID_ASSET_NAME);
        }

        model.editAsset(target, editedAsset);

        return String.format(MESSAGE_SUCCESS, editedAsset);
    }

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws IllegalArgumentException if the user input does not conform the expected format
     */
    public static EditAssetCommand of(String args) throws IllegalArgumentException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_OLD, PREFIX_NEW);

        if (!arePrefixesPresent(argMultimap, PREFIX_OLD, PREFIX_NEW)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new IllegalArgumentException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAssetCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_OLD, PREFIX_NEW);
        Asset target = Asset.of(argMultimap.getValue(PREFIX_OLD).get());
        Asset editedAsset = Asset.of(argMultimap.getValue(PREFIX_NEW).get());

        return new EditAssetCommand(target, editedAsset);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
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
        return target.equals(otherEditCommand.target)
                && editedAsset.equals(otherEditCommand.editedAsset);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("target", target)
                .add("editedAsset", editedAsset)
                .toString();
    }

}
