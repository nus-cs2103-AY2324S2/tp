package seedu.hirehub.logic.commands;

import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.hirehub.logic.commands.exceptions.CommandException;
import seedu.hirehub.model.Model;
import seedu.hirehub.model.person.SearchPredicate;

public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters the application list by the specified job "
            + "or candidate.\n"
            + "Parameters: [" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TITLE + "TITLE]"
            + "[" + PREFIX_STATUS + "STATUS]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "johndoe@example.com"
            + PREFIX_TITLE + "Software Engineer "
            + PREFIX_STATUS + "IN_PROGRESS";

    public static final String MESSAGE_NO_FIELD_PROVIDED = "Title or email to filter by must be provided.";

    private final SearchPredicate searchPredicate;

    public FilterCommand(SearchPredicate searchPredicate) {
        this.searchPredicate = searchPredicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
