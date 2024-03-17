package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import seedu.address.model.Model;

/**
 * Creates a group containing multiple unique CourseMates.
 * See {@code UniqueCourseMateList} for a the details of uniqueness.
 */
public class CreateGroupCommand extends Command {
    public static final String COMMAND_WORD = "create-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a group containing any number of CourseMates "
            + "CourseMates can be specified either by name or by the '#' notation"
            + "Parameters: NAME (cannot be empty and must be unique) "
            + "[" + PREFIX_COURSEMATE + "[COURSEMATE]\n" + "]"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_COURSEMATE + "#1 "
            + PREFIX_COURSEMATE + "John Doe.";


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from group");
    }
}
