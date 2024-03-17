package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.interview.Interview;

import java.util.List;

/**
 * Lists all persons in the address book to the user.
 */
public class ListInterviewsCommand extends Command {

    public static final String COMMAND_WORD = "list_interviews";

    public static final String MESSAGE_SUCCESS = "Listed all interviews";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Interview> interviewList = model.getFilteredInterviewList();
        String result = "";
        int x = 1;
        for (Interview i : interviewList) {
            result = result + "\n" + x + ") " + (i.toString()) + "\n";
            x++;
        }
        return new CommandResult(result);
    }
}
