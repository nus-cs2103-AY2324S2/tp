package seedu.address.logic.commands.articlecommands;

import seedu.address.logic.commands.Command;

/**
 * Represents a command with functionality pertaining to articles.
 */
public abstract class ArticleCommand extends Command {

    public String commandType() {
        return "articleCommand";
    };

}

