package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;

/**
 * Creates a group containing multiple unique CourseMates.
 * See {@code UniqueCourseMateList} for a the details of uniqueness.
 */
public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "create-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group containing any number of CourseMates, "
            + "CourseMates can be specified either by name or by the '#' notation. "
            + "Parameters: NAME (cannot be empty and must be unique) "
            + "[" + PREFIX_COURSEMATE + "[COURSEMATE]\n" + "]"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_COURSEMATE + "#1 "
            + PREFIX_COURSEMATE + "John Doe.";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the group list";

    private final Group toAdd;

    /**
     * Basic constructor for CreateGroupCommand. Creates a group with specified details.
     * @param name name of the group
     * @param courseMateSet set containing the courseMates in the group
     */
    public CreateGroupCommand(Name name, Set<CourseMate> courseMateSet) {
        requireAllNonNull(name, courseMateSet);
        toAdd = new Group(name, courseMateSet);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult(String.format("Hello from group: %s, Members: %s",
                toAdd.getName(), toAdd));
    }
}
