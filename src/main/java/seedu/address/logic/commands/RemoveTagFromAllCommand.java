package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.util.Pair;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;




/**
 * Removes all instance of specific tags associated to any person in the address book.
 */
public class RemoveTagFromAllCommand extends Command {

    public static final String COMMAND_WORD = "RemoveTagAll";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Removes the tag from all persons in the displayed person list.\n"
        + "Parameters: "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "friends";

    public static final String MESSAGE_REMOVE_TAG_SUCCESS = "Tag(s) removed from the following persons:\n%s";
    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "No persons have any of the tag(s)";

    private final Set<Tag> tags;
    private ArrayList<Pair<Person, List<Tag>>> removedTagList = new ArrayList<Pair<Person, List<Tag>>>();

    /**
     * @param tags to be removed from All persons
     */
    public RemoveTagFromAllCommand(Set<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        boolean hasTag = false;
        for (Person person : lastShownList) {
            List<Tag> tagList = new ArrayList<>();
            Set<Tag> oldTags = person.getTags();
            Set<Tag> newSet = new HashSet<>(oldTags);
            for (Tag tag : tags) {
                if (oldTags.contains(tag)) {
                    hasTag = true;
                    newSet.remove(tag);
                    tagList.add(tag);
                }
            }
            Person removedTagPerson = new Person(person.getName(), person.getPhone(),
                person.getEmail(), person.getAddress(), newSet);
            model.setPerson(person, removedTagPerson);
            if (tagList.size() > 0) {
                removedTagList.add(new Pair<>(person, tagList));
            }
        }
        if (!hasTag) {
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }

        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (model.shouldPurgeAddressBook()) {
            model.purgeAddressBook();
        }
        CommandResult removeTagFromAllCommandResult = new CommandResult(String.format(MESSAGE_REMOVE_TAG_SUCCESS,
                formatRemovedTags(removedTagList)));
        model.commitAddressBook(removeTagFromAllCommandResult);
        return removeTagFromAllCommandResult;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemoveTagFromAllCommand)) {
            return false;
        }

        RemoveTagFromAllCommand otherRemoveTagCommand = (RemoveTagFromAllCommand) other;
        return this.tags.equals(otherRemoveTagCommand.tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("tags", tags)
                .toString();
    }

    /**
     * Formats the removed tags into a string.
     */
    private String formatRemovedTags(List<Pair<Person, List<Tag>>> removedTagList) {
        StringBuilder sb = new StringBuilder();
        for (Pair<Person, List<Tag>> pair : removedTagList) {
            Person person = pair.getKey();
            List<Tag> removedTags = pair.getValue();
            sb.append(person.getName()).append(": ");
            for (int i = 0; i < removedTags.size(); i++) {
                sb.append(removedTags.get(i).toString());
                if (i < removedTags.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
