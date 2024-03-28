package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import seedu.address.logic.commands.SuggestMateCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.HasRequiredSkillsPredicate;
import seedu.address.model.coursemate.Name;

public class SuggestMateCommandParser implements Parser<SuggestMateCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the SuggestMateCommand
     * and returns a SuggestMateCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SuggestMateCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        try {
            Name groupName = ParserUtil.parseName(argMultiMap.getPreamble());
            return new SuggestMateCommand(groupName);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    SuggestMateCommand.MESSAGE_USAGE));
        }

    }
}
