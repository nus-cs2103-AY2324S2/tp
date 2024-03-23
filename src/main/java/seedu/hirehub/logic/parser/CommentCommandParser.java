package seedu.hirehub.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hirehub.logic.parser.CliSyntax.PREFIX_COMMENT;

import seedu.hirehub.commons.core.index.Index;
import seedu.hirehub.logic.commands.CommentCommand;
import seedu.hirehub.logic.commands.StatusCommand;
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

        if (parsedIndexComment.length < 2 || parsedIndexComment[1].trim().equals("")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(parsedIndexComment[0]);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommentCommand.MESSAGE_USAGE), pe);
        }

        Comment comment = new Comment(parsedIndexComment[1]);

        return new CommentCommand(index, comment);
    }
}
