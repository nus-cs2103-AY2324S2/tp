package seedu.address.logic.commands;


import seedu.address.model.Model;

/**
 * Creates a group containing multiple unique CourseMates.
 * See {@code UniqueCourseMateList} for a the details of uniqueness.
 */
public class CreateGroupCommand extends Command{
    public static final String COMMAND_WORD = "create-group";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Hello from group");
    }
}
