package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.contact.TsContainsKeywordsPredicate;

import java.util.List;

/**
 * Finds and lists all contacts in address book whose tech stack contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindTechStackCommand extends Command {

    public static final String COMMAND_WORD = "find-ts";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts whose tech stacks contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " java python ";

    private final List<String> techKeywords;

    public FindTechStackCommand(List<String> techKeywords) {
        this.techKeywords = techKeywords;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        TsContainsKeywordsPredicate predicate = new TsContainsKeywordsPredicate(techKeywords);
        model.updateFilteredContactList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_CONTACT_LISTED_OVERVIEW, model.getFilteredContactList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindTechStackCommand)) {
            return false;
        }

        FindTechStackCommand otherFindTechStackCommand = (FindTechStackCommand) other;
        return techKeywords.equals(otherFindTechStackCommand.techKeywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", techKeywords)
                .toString();
    }

}
