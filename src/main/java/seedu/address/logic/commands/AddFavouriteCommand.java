package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.List;

public class AddFavouriteCommand extends Command {
    public static final String COMMAND_WORD = "addfav";

    public static final String MESSAGE_SUCCESS = "The following contacts have been added to favourites: %s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds contacts identified by index number "
            + "as favourites.\n"
            + "Parameters: i/ [INDICES] (must be a positive integer) separated by commas\n"
            + "Example: " + COMMAND_WORD
            + "i/ 1,2,5";

    private final List<Index> indices;

    public AddFavouriteCommand(List<Index> indices) {
        requireNonNull(indices);
        this.indices = indices;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> people = model.getFilteredPersonList();
        List<String> modifiedContacts = new ArrayList<>();
        for (Index index : this.indices) {
            if (index.getZeroBased() >= people.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person person = people.get(index.getZeroBased());
            modifiedContacts.add(person.getName().fullName);
            Set<Tag> tags = new HashSet<>(person.getTags());
            tags.add(new Tag("Favourite"));
            EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
            editPersonDescriptor.setTags(tags);
            EditCommand editCommand = new EditCommand(index, editPersonDescriptor);
            editCommand.execute(model);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, modifiedContacts));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddFavouriteCommand)) {
            return false;
        }

        AddFavouriteCommand otherAddOrderCommand = (AddFavouriteCommand) other;
        return this.indices.equals(otherAddOrderCommand.indices);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.indices.forEach(sb::append);
        return new ToStringBuilder(this)
                .add("indices", sb.toString())
                .toString();
    }
}
