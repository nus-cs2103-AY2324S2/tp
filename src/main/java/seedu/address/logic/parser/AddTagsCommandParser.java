package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;



/**
 * Parses input arguments and creates a new AddTagsCommand object
 */
public class AddTagsCommandParser implements Parser<AddTagsCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddTagsCommand
     * and returns an AddTagsCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTagsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagsCommand.MESSAGE_USAGE), pe);
        }

        List<String> tagList = argMultimap.getAllValues(PREFIX_TAG);

        if (tagList.isEmpty()) {
            throw new ParseException(AddTagsCommand.MESSAGE_NOT_ADDED);
        }

        Set<Tag> tags = ParserUtil.parseTags(tagList);

        return new AddTagsCommand(index, tags);
    }

}
