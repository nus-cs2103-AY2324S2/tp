package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.group.Group;

/**
 * Creates a group containing multiple unique CourseMates.
 * See {@code UniqueCourseMateList} for a the details of uniqueness.
 */
public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "create-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group containing any number of CourseMates, "
            + "CourseMates can be specified either by name or by the '#' notation.\n"
            + "Parameters: NAME (cannot be empty and must be unique) "
            + "[" + PREFIX_COURSEMATE + " COURSEMATE" + "]\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_COURSEMATE + " #1 "
            + PREFIX_COURSEMATE + " John Doe.";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the group list.";

    public static final String MESSAGE_GROUP_CREATED = "A group with name: %s was created.";

    public static final String MESSAGE_MEMBERS_DONT_EXIST = "Some of the included members could not be found.";

    private final Name groupName;
    private final Set<QueryableCourseMate> queryableCourseMateSet;

    /**
     * Basic constructor for {@code CreateGroupCommand}.
     * Creates the details for a group to be created.
     * @param groupName name of the group
     * @param queryableCourseMateSet set containing the queryableCourseMate in the group
     */
    public CreateGroupCommand(Name groupName, Set<QueryableCourseMate> queryableCourseMateSet) {
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
                    .map(x -> x.get(0))
                    .collect(Collectors.toSet());
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(MESSAGE_MEMBERS_DONT_EXIST);
        }

        Group toAdd = new Group(groupName, courseMateList);
        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }
        model.addGroup(toAdd);

        return new CommandResult(String.format(MESSAGE_GROUP_CREATED, groupName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CreateGroupCommand)) {
            return false;
        }

        CreateGroupCommand otherCreateGroupCommand = (CreateGroupCommand) other;
        return otherCreateGroupCommand.groupName.equals(groupName)
                && otherCreateGroupCommand.queryableCourseMateSet.equals(queryableCourseMateSet);
    }
}
