package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * Deletes member(s) from a preexisting group.
 */
public class DeleteMemberCommand extends Command {
    public static final String COMMAND_WORD = "delete-member";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes CourseMates from an existing group."
            + "CourseMates can be specified either by name or by the '#' notation.\n"
            + "Parameters: NAME (group must exist) "
            + PREFIX_COURSEMATE + " COURSEMATE"
            + "[" + PREFIX_COURSEMATE + " COURSEMATE" + "]\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_COURSEMATE + " #1 "
            + PREFIX_COURSEMATE + " John Doe.";
    public static final String MESSAGE_MEMBERS_NOT_IN_GROUP =
            "Some of the specified members are not in the group.";
    public static final String MESSAGE_SUCCESFULLY_REMOVED = "Group successfully modified, Name: %s";

    private final Name groupName;
    private final Set<QueryableCourseMate> queryableCourseMateSet;

    /**
     * Basic constructor for {@code DeleteMemberCommand}.
     * @param queryableCourseMateSet set containing the queryableCourseMate to be deleted
     */
    public DeleteMemberCommand(Name groupName, Set<QueryableCourseMate> queryableCourseMateSet) {
        requireAllNonNull(groupName, queryableCourseMateSet);
        this.groupName = groupName;
        this.queryableCourseMateSet = queryableCourseMateSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<CourseMate> courseMateList;
        try {
            courseMateList = queryableCourseMateSet
                    .stream()
                    .map(model::findCourseMate)
                    .collect(Collectors.toSet());
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_MEMBERS_DONT_EXIST, e);
        }

        Group toModify;
        try {
            toModify = model.findGroup(groupName);
        } catch (GroupNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_NAME);
        }

        Group modifiedGroup = new Group(toModify.getName(), toModify.asUnmodifiableObservableList());
        try {
            for (CourseMate courseMate: courseMateList) {
                modifiedGroup.remove(courseMate);
            }
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(MESSAGE_MEMBERS_NOT_IN_GROUP, e);
        }

        model.setGroup(toModify, modifiedGroup);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(
                String.format(MESSAGE_SUCCESFULLY_REMOVED, groupName), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteMemberCommand)) {
            return false;
        }

        DeleteMemberCommand otherDeleteMemberCommand = (DeleteMemberCommand) other;
        return otherDeleteMemberCommand.groupName.equals(groupName)
                && otherDeleteMemberCommand.queryableCourseMateSet.equals(queryableCourseMateSet);
    }
}
