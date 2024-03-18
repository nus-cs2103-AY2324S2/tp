package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonFieldsContainKeywordPredicate;

public class FindCommand extends Command {
    
    public static final String COMMAND_WORD = "find";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons based on the specified category and description "
        + "and displays them as a list with index numbers. Use prefixes 'c/' for category and 'd/' for description.\n"
        + "Parameters: c/CATEGORY d/DESCRIPTION\n"
        + "Example: " + COMMAND_WORD + " c/email d/johndoe@example.com";
    
    private final PersonFieldsContainKeywordPredicate predicate;
    
    public FindCommand(PersonFieldsContainKeywordPredicate predicate) {
        this.predicate = predicate;
    }
    
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        
        if (!(other instanceof FindCommand)) {
            return false;
        }
        
        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }
    
    @Override
    public String toString() {
        return "FindCommand{" +
            "predicate=" + predicate +
            '}';
    }
}
