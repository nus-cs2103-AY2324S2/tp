package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Entry;
import seedu.address.model.person.Person;

/**
 * Class for add category command
 */
public class AddCategoryCommand extends Command {
    public static final String COMMAND_WORD = "addCategory";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add a category to a person "
            + "by the index number used in the displayed person list.\n "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_CATEGORY + "CATEGORY NAME] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_CATEGORY + "CLAN "
            + PREFIX_DESCRIPTION + "KINGDOMS";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Added Category %1$s to Person %2$s";
    public static final String ENTRY_NOT_ADDED = "Both fields to add must be provided.";
    public static final String MESSAGE_DUPLICATE_CATEGORY = "This category already exists for this person.";

    private final Index index;

    private final Entry entry;

    /**
     * @param index of the person in the filtered person list to edit
     * @param entry the person
     */
    public AddCategoryCommand(Index index, Entry entry) {
        requireNonNull(index);
        requireNonNull(entry);

        this.index = index;
        this.entry = entry;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        //Gets the list of already existing person
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
        //Get the person you want to edit based on index
        Person personToEdit = lastShownList.get(index.getZeroBased());
        Entry e = personToEdit.getEntry(entry.getCategory());

        if (e != null) {
            throw new CommandException(MESSAGE_DUPLICATE_CATEGORY);
        }
        personToEdit.addEntry(entry);
        model.setPerson(personToEdit, personToEdit);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(
                MESSAGE_EDIT_PERSON_SUCCESS, entry.getCategory(), Messages.format(personToEdit)
        ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCategoryCommand)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("entry", entry)
                .toString();
    }
}