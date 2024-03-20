package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;

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

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        for (StudentId studentId : studentIds) {
            EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
            editPersonDescriptor.setGroups(group);
            EditCommand editCommand = new EditCommand(studentId, editPersonDescriptor);
            editCommand.execute(model);
        }
        return new CommandResult("---");
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
