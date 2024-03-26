package seedu.findvisor.logic.parser;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.AddtagCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;
import seedu.findvisor.model.tag.Tag;

public class AddtagCommandParser implements Parser<AddtagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddtagCommand
     * and returns an AddtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddtagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        Index index;
        Set<Tag> newTags;

        if (!arePrefixesPresent(argMultimap, PREFIX_TAG)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddtagCommand.MESSAGE_USAGE));
        }

        index = ParserUtil.parseIndex(argMultimap.getPreamble());

        try {
            newTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddtagCommand.MESSAGE_TAG_CONSTRAINTS_VIOLATED), pe);
        }

        return new AddtagCommand(index, newTags);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
