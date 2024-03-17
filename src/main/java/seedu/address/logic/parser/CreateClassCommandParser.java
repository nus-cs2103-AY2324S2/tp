package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Classes;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;

import java.util.stream.Stream;

/**
 * Parses user input and creates a new CreateClassCommand.
 */
public class CreateClassCommandParser implements Parser<CreateClassCommand> {

    @Override
    public CreateClassCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(userInput, PREFIX_CLASS);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_CLASS);
        Classes classes = ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS).get());

        return new CreateClassCommand(classes);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
