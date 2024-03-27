package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonCompanyNameComparator;
import seedu.address.model.person.PersonInterviewTimeComparator;
import seedu.address.model.person.PersonNameComparator;
import seedu.address.model.person.PersonPriorityComparator;
import seedu.address.model.person.PersonSalaryComparator;

/**
 * Sorts the list of contacts based on a specified information.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final Object MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list of contacts "
            + "based on a specified information. \n"
            + "Parameters: PERSON ATTRIBUTE (only hashable types)"
            + "[" + PREFIX_COMPANY_NAME + "COMPANY NAME] "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_INTERVIEWTIME + "INTERVIEW-TIME] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "Example: " + COMMAND_WORD + PREFIX_INTERVIEWTIME;

    public static final String MESSAGE_LIST_SORTED_SUCCESS = "List Sorted";
    private final Integer info;

    /**
     * Creates a SortCommand to sort the list of contacts based on the specified information.
     */
    public SortCommand(Integer info) {
        requireNonNull(info);
        this.info = info;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        switch (info) {
        case 0:
            model.updateSortedPersonList(new PersonPriorityComparator());
            break;
        case 1:
            model.updateSortedPersonList(new PersonCompanyNameComparator());
            break;
        case 2:
            model.updateSortedPersonList(new PersonNameComparator());
            break;
        case 3:
            model.updateSortedPersonList(new PersonInterviewTimeComparator());
            break;
        case 4:
            model.updateSortedPersonList(new PersonSalaryComparator());
            break;
        default:
            throw new CommandException(Messages.MESSAGE_INVALID_SORT_COMMAND_INDEX);
        }
        return new CommandResult(MESSAGE_LIST_SORTED_SUCCESS);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand otherSort = (SortCommand) other;
        return info.equals(otherSort.info);
    }
}
