package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;

import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.model.Model;
import scrolls.elder.model.PersonStore;
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
    private final Boolean isSearchingVolunteer;
    private final Boolean isSearchingBefriendee;

    /**
     * Creates a FindCommand to find the specified {@code NameContainsKeywordsPredicate}
     */
    public FindCommand(NameContainsKeywordsPredicate predicate,
                       Boolean isSearchingVolunteer, Boolean isSearchingBefriendee) {
        this.predicate = predicate;
        this.isSearchingBefriendee = isSearchingBefriendee;
        this.isSearchingVolunteer = isSearchingVolunteer;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);

        PersonStore store = model.getMutableDatastore().getMutablePersonStore();

        assert (isSearchingVolunteer || isSearchingBefriendee)
                : "At least one or both isSearchingVolunteer and isSearchingBefriendee should be true.";

        if (isSearchingVolunteer && isSearchingBefriendee) {
            store.updateFilteredPersonList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, store.getFilteredPersonList().size()));

        } else if (isSearchingVolunteer) {
            store.updateFilteredVolunteerList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_WITH_ROLE,
                            store.getFilteredVolunteerList().size(),
                            "volunteer"));

        } else {
            store.updateFilteredBefriendeeList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW_WITH_ROLE,
                            store.getFilteredBefriendeeList().size(),
                            "befriendee"));
        }

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
                && isSearchingVolunteer.equals(otherFindCommand.isSearchingVolunteer)
                && isSearchingBefriendee.equals(otherFindCommand.isSearchingBefriendee);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .add("isSearchingVolunteer", isSearchingVolunteer)
                .add("isSearchingBefriendee", isSearchingBefriendee)
                .toString();
    }
}
