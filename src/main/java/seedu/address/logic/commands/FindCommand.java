package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.startup.FundingStageContainsKeywordsPredicate;
import seedu.address.model.startup.IndustryContainsKeywordsPredicate;
import seedu.address.model.startup.NameContainsKeywordsPredicate;
import seedu.address.model.startup.Startup;


/**
 * Finds and lists all startups in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all startups that contain any of "
            + "the specified keywords (case-insensitive) of either name / industry / funding stage"
            + " and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " n/ Names \n"
            + "Example: " + COMMAND_WORD + " i/ Industries \n"
            + "Example: " + COMMAND_WORD + " f/ Funding Stages \n"
            + "Example: " + COMMAND_WORD + " f/ B C";;

    private final Predicate<Startup> predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    public FindCommand(IndustryContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }
    public FindCommand(FundingStageContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStartupList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STARTUPS_LISTED_OVERVIEW, model.getFilteredStartupList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
