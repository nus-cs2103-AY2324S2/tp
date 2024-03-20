package seedu.edulink.logic.commands;

import static seedu.edulink.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.student.Student;
import seedu.edulink.model.tag.Tag;

/**
 * Adds tags to a student.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_PERSON_NOTFOUND = "Can't find the person you specified.";
    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added Tags: %1$s";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_ID + "ID " + PREFIX_TAG + "Tag";
    public static final String MESSAGE_DUPLICATE = "Note, Some tags you input are duplicated. Duplication is removed.";

    private final Id personToEditId;
    private final Set<Tag> tags;

    /**
     * Creates a TagCommand to add tags to a student.
     *
     * @param personToEditId the ID of the student user add tags to.
     * @param tags           a set of tags that the user wish to add to the student.
     */
    public TagCommand(Id personToEditId, Set<Tag> tags) {
        requireAllNonNull(personToEditId, tags);

        this.personToEditId = personToEditId;
        this.tags = tags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredPersonList();
        Student studentToEdit = null;
        for (Student student : lastShownList) {
            if (student.getId().equals(personToEditId)) {
                studentToEdit = student;
            }
        }
        if (studentToEdit == null) {
            throw new CommandException(MESSAGE_PERSON_NOTFOUND);
        }
        Set<Tag> resultTags = studentToEdit.getTags();
        Set<Tag> mergedSet = new HashSet<>(resultTags);
        mergedSet.addAll(tags);

        Student editedStudent = new Student(studentToEdit.getId(), studentToEdit.getMajor(), studentToEdit.getIntake(),
            studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
            studentToEdit.getAddress(), mergedSet);

        model.setPerson(studentToEdit, editedStudent);
        if (mergedSet.size() != resultTags.size() + tags.size()) {
            return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags)
                + " \n" + MESSAGE_DUPLICATE);
        }

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, tags));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        boolean isStudentIdEqual = this.personToEditId.equals(otherTagCommand.personToEditId);
        boolean isTagListEqual = this.tags.equals(otherTagCommand.tags);
        return (isStudentIdEqual && isTagListEqual);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("Id", this.personToEditId)
            .add("Tags", this.tags)
            .toString();
    }
}
