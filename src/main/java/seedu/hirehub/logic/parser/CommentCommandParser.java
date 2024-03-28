package seedu.hirehub.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.commands.CommentCommand;
import seedu.hirehub.logic.parser.exceptions.ParseException;
import seedu.hirehub.model.person.Comment;

/**
 * Parses input arguments and creates a new CommentCommand object
 */
public class CommentCommandParser implements Parser<CommentCommand> {
    @Override
    public CommentCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String[] parsedIndexComment = args.trim().split(" ", 2);

        Index index;
        try {
            index = ParserUtil.parseIndex(parsedIndexComment[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommentCommand.MESSAGE_USAGE), pe);
        }

        Comment comment = new Comment(
                parsedIndexComment.length == 1
                ? ""
                : parsedIndexComment[1].trim()
        );

        return new CommentCommand(index, comment);
    }
}
