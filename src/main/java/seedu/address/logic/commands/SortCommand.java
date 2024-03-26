package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.model.person.PersonPriorityComparator;

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

    private final Integer info;

    //sort | tt/ |
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
        }
        return new CommandResult("SORRRRTEEEDDDD TEEHEEE GG EZ");
    }
}
