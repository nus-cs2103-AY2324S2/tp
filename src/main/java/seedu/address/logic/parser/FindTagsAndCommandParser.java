package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.Set;

import seedu.address.logic.commands.FindTagsAndCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagsAndFoundPredicate;

/**
 * Parses input arguments and creates a new FindTagsOrCommand object
 */
public class FindTagsAndCommandParser implements Parser<FindTagsAndCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindTagsOrCommand
     * and returns a FindTagsOrCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindTagsAndCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTagsAndCommand.MESSAGE_USAGE));
        }

        String[] tags = trimmedArgs.split("\\s+");
        Set<Tag> tagSet = ParserUtil.parseTags(Arrays.asList(tags));

        return new FindTagsAndCommand(new TagsAndFoundPredicate(tagSet));
    }

}
