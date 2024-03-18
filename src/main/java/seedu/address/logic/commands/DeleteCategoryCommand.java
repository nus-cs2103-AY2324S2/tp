package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;

/**
 * Deletes one existing category of an existing person in the address book.
 */
public class DeleteCategoryCommand extends Command {

    public static final String COMMAND_WORD = "deleteCategory";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the category of person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CATEGORY + "CATEGORY\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "Email";

    public static final String MESSAGE_DELETE_CATEGORY_SUCCESS = "Deleted Category %1$s of Person %2$s";
    public static final String MESSAGE_CATEGORY_DOES_NOT_EXIST = "Category doesn't exist.";

    public static final String MESSAGE_WRONG_CATEGORY = "This person does not have this category.";

    private final Index targetIndex;

    private final String category;

    /**
     * @param targetIndex of the person in the filtered person list to edit
     * @param category    the category needed to be deleted
     */
    public DeleteCategoryCommand(Index targetIndex, String category) {
        this.targetIndex = targetIndex;
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());
        Entry entry = personToEdit.getEntry(category);
        if (entry == null) {
            throw new CommandException(MESSAGE_CATEGORY_DOES_NOT_EXIST);
        }

        Entry e = personToEdit.getEntry(entry.getCategory());
        if (e == null) {
            throw new CommandException(MESSAGE_WRONG_CATEGORY);
        } else {
            personToEdit.deleteEntry(entry.getCategory());
        }

        model.setPerson(personToEdit, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(
                MESSAGE_DELETE_CATEGORY_SUCCESS, category, Messages.format(personToEdit)
        ));
    }
}
