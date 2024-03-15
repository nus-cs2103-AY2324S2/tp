package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.Model;
import seedu.address.model.person.Id;
/**
 * Changes the remark of an existing person in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    private final Id studentID;
    private final String tag;

    public TagCommand (Id studentID, String tag) {
        requireAllNonNull(studentID, tag);

        this.studentID= studentID;
        this.tag = tag;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Successfully Tagged a student."+studentID.toString()+tag);
    }
}
