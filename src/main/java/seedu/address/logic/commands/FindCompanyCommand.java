package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.model.person.CompanyContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose company tag contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCompanyCommand extends Command {

    public static final String COMMAND_WORD = "findco";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts with company tag containing "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + "TikTok";

    public static final String MESSAGE_FIND_COMPANY_SUCCESS = "Found %1$d %2$s with matching company tag(s).";

    private final CompanyContainsKeywordsPredicate predicate;

    public FindCompanyCommand(CompanyContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        int numContacts = model.getFilteredPersonList().size();
        String contactOrContacts = (numContacts == 1) ? "contact" : "contacts";
        return new CommandResult(String.format(MESSAGE_FIND_COMPANY_SUCCESS, numContacts, contactOrContacts));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCompanyCommand)) {
            return false;
        }

        FindCompanyCommand otherFindCompanyCommand = (FindCompanyCommand) other;
        return predicate.equals(otherFindCompanyCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
