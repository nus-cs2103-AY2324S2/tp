package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_FILENAME;

import seedu.edulink.logic.Messages;
import seedu.edulink.logic.commands.ExportCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExportCommand Object
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    @Override
    public ExportCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_FILENAME);


        if (argMultimap.getValue(PREFIX_FILENAME).isEmpty()) {
            throw new ParseException(String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                ExportCommand.MESSAGE_USAGE));
        }
        String filename = argMultimap.getValue(PREFIX_FILENAME).get();
        return new ExportCommand(ParserUtil.parseFilename(filename));
    }
}
