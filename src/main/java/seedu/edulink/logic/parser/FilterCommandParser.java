package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.edulink.logic.commands.FilterCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.student.TagsContainQueryTagsPredicate;
import seedu.edulink.model.tag.Tag;


/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterComman object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Set<Tag> tagList;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (argMultimap.getAllValues(PREFIX_TAG).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        for (Tag tag : tagList) {
            if (tag.tagName.trim().isEmpty()) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
            }
        }

        return new FilterCommand(new TagsContainQueryTagsPredicate(tagList));
    }

}
