package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;

import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.model.Model;
import scrolls.elder.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-insensitive), displays them in the respective lists with index numbers.\n"
            + "Parameters: [r/ROLE] KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;
    private final Boolean searchVolunteer;
    private final Boolean searchBefriendee;

    /**
     * Creates a FindCommand to find the specified {@code NameContainsKeywordsPredicate}
     */
    public FindCommand(NameContainsKeywordsPredicate predicate, Boolean searchVolunteer, Boolean searchBefriendee) {
        this.predicate = predicate;
        this.searchBefriendee = searchBefriendee;
        this.searchVolunteer = searchVolunteer;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        if (searchVolunteer && searchBefriendee) {
            model.updateFilteredPersonList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));

        } else if (searchVolunteer) {
            model.updateFilteredVolunteerList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_WITH_ROLE,
                            model.getFilteredVolunteerList().size(),
                            "volunteer"));

        } else if (searchBefriendee) {
            model.updateFilteredBefriendeeList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_WITH_ROLE,
                            model.getFilteredBefriendeeList().size(),
                            "befriendee"));
        }

        assert false : "Invalid Find parameters, searchVolunteer, searchBefriendee cannot be false at the same time.";

        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
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
        return predicate.equals(otherFindCommand.predicate)
                && searchVolunteer.equals(otherFindCommand.searchVolunteer)
                && searchBefriendee.equals(otherFindCommand.searchBefriendee);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .add("searchVolunteer", searchVolunteer)
                .add("searchBefriendee", searchBefriendee)
                .toString();
    }
}
