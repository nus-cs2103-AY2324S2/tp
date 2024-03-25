package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONEY_OWED;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.MoneyOwed;
import seedu.address.model.person.Person;

/**
 * Splits the sum of money owed among a group of person using the displayed
 * index from the address book.
 */
public class SplitCommand extends Command {
    public static final String COMMAND_WORD = "split";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Splits the sum of money owed among a group of person "
            + "using the displayed index from the address book.\n"
            + "Parameters: at least one INDEX (must be a positive integer) "
            + PREFIX_MONEY_OWED + "MONEY_OWED "
            + "Example: " + COMMAND_WORD + " 1 2 "
            + PREFIX_MONEY_OWED + "4.50";
    private List<Index> indexListToSplit;
    private MoneyOwed totalOwed;
    /**
     * Returns a new FilterCommand object that takes in a PersonHasTagPredicate
     * to update the filtered list
     * @param indexListToSplit
     * @param totalOwed
     */
    public SplitCommand(List<Index> indexListToSplit, MoneyOwed totalOwed) {
        this.indexListToSplit = indexListToSplit;
        this.totalOwed = totalOwed;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        String splitAmountInString = String.format("%.2f", totalOwed.getAmount() / indexListToSplit.size());
        Float splitAmount = Float.parseFloat(splitAmountInString);

        for (Index index : indexListToSplit) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson = new Person(
                    personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(), personToEdit.getAddress(),
                    personToEdit.getRemark(), personToEdit.getTags(), personToEdit.getBirthday(),
                    new MoneyOwed(String.valueOf(personToEdit.getMoneyOwed().getAmount() + splitAmount)));
            //personToEdit.getMoneyOwed().addAmountOwed(splitAmount)
            //new MoneyOwed(String.valueOf(personToEdit.getMoneyOwed().getAmount() + splitAmount))
            model.setPerson(personToEdit, editedPerson);
        }
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SplitCommand)) {
            return false;
        }

        SplitCommand otherSplitCommand = (SplitCommand) other;
        return indexListToSplit.equals(otherSplitCommand.indexListToSplit)
                && totalOwed.equals(otherSplitCommand.totalOwed);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("indexListToSplit", indexListToSplit)
                .add("totalOwed", totalOwed)
                .toString();
    }
}
