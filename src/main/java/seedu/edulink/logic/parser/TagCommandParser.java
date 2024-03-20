package seedu.edulink.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.edulink.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.edulink.logic.commands.TagCommand;
import seedu.edulink.logic.parser.exceptions.ParseException;
import seedu.edulink.model.student.Id;
import seedu.edulink.model.tag.Tag;


/**
 * Parse input arguments and create a TagCommand object.
 */
public class TagCommandParser implements Parser<TagCommand> {

    /**
     * Parses the user's arguments to TagCommand object.
     *
     * @param args the user's argument.
     * @return the new TagCommand object.
     * @throws ParseException if the user's input doesn't conform the expected format.
     */
    public TagCommand parse(String args) throws ParseException {
        Id userId;
        Set<Tag> tagList;
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ID,
            PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_ID, PREFIX_TAG)
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }
        try {
            String userIdString = argMultimap.getValue(PREFIX_ID).get();
            userId = new Id(userIdString);
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (IllegalArgumentException e) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }

        return new TagCommand(userId, tagList);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
