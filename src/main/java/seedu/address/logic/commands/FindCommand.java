package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSTHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MORETHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REFLECTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDIO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.PersonDetailContainsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final Prefix[] ACCEPTED_PREFIXES = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                                                      PREFIX_ADDRESS, PREFIX_TAG, PREFIX_MATRIC_NUMBER,
                                                      PREFIX_REFLECTION, PREFIX_STUDIO, PREFIX_LESSTHAN,
                                                      PREFIX_MORETHAN};


    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts with a specified aspect "
            + "containing specified keyword (case-insensitive) or\n"
            + "which meets the criteria with the specified value (positive-integer)\n"
            + "and displays them as a list with index numbers.\n"
            + "The currently supported prefixes are: "
            + Arrays.toString(ACCEPTED_PREFIXES) + "\n"
            + "Parameters: PREFIX/KEYWORD\n"
            + "Example: " + COMMAND_WORD + " n/Alice";

    public static final String MESSAGE_SCORE_GREATER_THAN_MAX = "Value cannot be greater than the maximum score.";

    private final PersonDetailContainsKeywordPredicate predicate;

    public FindCommand(PersonDetailContainsKeywordPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Exam selectedExam = model.getSelectedExam().getValue();
        if (predicate.isExamRequired()) {
            if (!isAnyNonNull(selectedExam)) {
                throw new CommandException(Messages.MESSAGE_NO_EXAM_SELECTED);
            }
            if (selectedExam.getMaxScore().getScore() < Integer.parseInt(predicate.getKeyword())) {
                throw new CommandException(MESSAGE_SCORE_GREATER_THAN_MAX);
            }
            model.updateFilteredPersonList(predicate.withExam(selectedExam));
        } else {
            model.updateFilteredPersonList(predicate);
        }
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
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
