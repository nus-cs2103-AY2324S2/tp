package seedu.address.logic.commands.articlecommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_ARTICLES;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;

/**
 * Lists all articles in the article book to the user.
 */
public class ListArticleCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredArticleList(PREDICATE_SHOW_ALL_ARTICLES);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
