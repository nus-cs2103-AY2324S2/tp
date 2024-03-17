package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.tag.Tag;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;


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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,PREFIX_ID,
                PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap, PREFIX_ID,PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagCommand.MESSAGE_USAGE));
        }
        try {
            String userIdString =argMultimap.getValue(PREFIX_ID).get();
            userId = new Id(userIdString);
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        }catch(IllegalArgumentException e) {
            throw new ParseException("Invalid userID");
        }

        return new TagCommand(userId, tagList);
    }
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
