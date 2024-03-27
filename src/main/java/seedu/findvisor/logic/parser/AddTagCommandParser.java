package seedu.findvisor.logic.parser;
import static seedu.findvisor.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.findvisor.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.findvisor.commons.core.index.Index;
import seedu.findvisor.logic.commands.AddTagCommand;
import seedu.findvisor.logic.parser.exceptions.ParseException;
import seedu.findvisor.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddtagCommand object
 */
public class AddTagCommandParser implements Parser<AddTagCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddtagCommand
     * and returns an AddtagCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        Index index;
        Set<Tag> newTags;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE), pe);
        }

        if (!argMultimap.getValue(PREFIX_TAG).isPresent()) {
            //if there is not at least 1 tag present, throw exception
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
        }

        try {
            newTags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTagCommand.MESSAGE_TAG_CONSTRAINTS_VIOLATED), pe);
        }

        return new AddTagCommand(index, newTags);
    }

}
