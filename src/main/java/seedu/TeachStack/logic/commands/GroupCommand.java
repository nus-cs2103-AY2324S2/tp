package seedu.TeachStack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.TeachStack.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.TeachStack.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Set;

import seedu.TeachStack.commons.util.ToStringBuilder;
import seedu.TeachStack.logic.commands.exceptions.CommandException;
import seedu.TeachStack.model.Model;
import seedu.TeachStack.model.group.Group;
import seedu.TeachStack.model.person.StudentId;


/**
 * Creates a group with the given group name and IDs.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a group with people corresponding to the selected IDs. "
            + "Parameters: "
            + "[" + PREFIX_GROUP + "GROUP_NAME] "
            + "[" + PREFIX_STUDENTID + "STUDENTID] "
            + "... (multiple IDs allowed) "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GROUP + "3 \n"
            + PREFIX_STUDENTID + "A0123456X "
            + PREFIX_STUDENTID + "A0123456H ";

    public static final String MESSAGE_GROUP_SUCCESS = "All students were added!";
    public static final String STUDENTS_NOT_FOUND = "The following IDs were not found (and not added to the group): ";
    private final Set<Group> group; //set with one group. for compatibility with edit command.
    private final Set<StudentId> studentIds;

    /**
     * @param group A Set of type Group with only one Group.
     * @param studentIds A Set of studentIds with possibly multiple Ids.
     */
    public GroupCommand(Set<Group> group, Set<StudentId> studentIds) {
        requireNonNull(group);
        requireNonNull(studentIds);
        this.group = group;
        this.studentIds = studentIds;
    }

    /**
     * @param model {@code Model} which the command should operate on.
     * @return a new CommandResult.
     * @throws CommandException if there are ANY students not found at all.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        String missingIds = "";
        requireNonNull(model);
        for (StudentId studentId : studentIds) {
            EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
            editPersonDescriptor.setGroups(group);
            EditCommand editCommand = new EditCommand(studentId, editPersonDescriptor);
            try {
                editCommand.execute(model);
            } catch (CommandException e) {
                missingIds += studentId + " ";
            }
            //there's a dependency on EditCommand, but this for loop shouldn't cause EditCommand to throw any exception.
        }
        if (missingIds.equals("")) {
            return new CommandResult(MESSAGE_GROUP_SUCCESS);
        } else {
            throw new CommandException(STUDENTS_NOT_FOUND + missingIds);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCommand)) {
            return false;
        }

        GroupCommand otherGroupCommand = (GroupCommand) other;
        return group.equals(otherGroupCommand.group) && studentIds.equals(otherGroupCommand.studentIds);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("group", group)
                .add("studentIds", studentIds)
                .toString();
    }
}
