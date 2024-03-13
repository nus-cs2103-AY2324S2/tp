package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FindAndExportCommand;
import seedu.address.logic.parser.exceptions.ParseException;


public class FindAndExportCommandParser implements Parser<FindAndExportCommand> {

    public FindAndExportCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_FILENAME);
        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindAndExportCommand.MESSAGE_USAGE));
        }
        String tag = argMultimap.getPreamble().trim();
        String name = argMultimap.getValue(PREFIX_NAME).orElse(null);
        String address = argMultimap.getValue(PREFIX_ADDRESS).orElse(null);
        String filename = argMultimap.getValue(PREFIX_FILENAME).orElse("default_filename.csv");

        return new FindAndExportCommand(tag, name, address, filename);
    }
}
