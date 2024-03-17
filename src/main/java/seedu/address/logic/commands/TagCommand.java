package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adds tags to a student.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added Tags: %1$s";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD+" " + PREFIX_ID + "ID " + PREFIX_TAG + "Tag";
    public static final String MESSAGE_DUPLICATE = "Note, Some tags you input are duplicated. Duplication is removed.";

    private final Id personToEditId;
    private final Set<Tag> tags;

    /**
     * Creates a TagCommand to add tags to a student.
     *
     * @param personToEditId the ID of the student user add tags to.
     * @param tags a set of tags that the user wish to add to the student.
     */
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
        if (mergedSet.size() != resultTags.size()+tags.size()){
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags) + " \n"+ MESSAGE_DUPLICATE);
        }

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags));
    }
}
