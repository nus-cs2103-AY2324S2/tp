package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Changes the remark of an existing person in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added Tags: %1$s";

    private final Id personToEditId;
    private final Set<Tag> tags;

    public TagCommand (Id personToEditId, Set<Tag> tags) {
        requireAllNonNull(personToEditId, tags);

        this.personToEditId= personToEditId;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException{
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = null;
        for (Person person:lastShownList){
            if (person.getId().equals(personToEditId)){
                personToEdit = person;
            }
        }
        if (personToEdit == null){
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Set<Tag> resultTags = personToEdit.getTags();
        Set<Tag> mergedSet = new HashSet<>(resultTags);
        mergedSet.addAll(tags);

        Person editedPerson = new Person(personToEdit.getId(),personToEdit.getMajor(),personToEdit.getIntake(),
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), mergedSet);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags));
    }
}
