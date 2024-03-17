package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.tag.Tag;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class TagCommandParser implements Parser<TagCommand> {
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,PREFIX_ID,
                PREFIX_TAG);

        Id userId;
        String tagMessage;
        try {
            userId = new Id(argMultimap.getValue(PREFIX_ID).get());
            tagMessage = argMultimap.getValue(PREFIX_TAG).get();
        }catch(IllegalArgumentException e) {
            throw new ParseException("Invalid userID");
        }
            Tag tag = new Tag(tagMessage);
            Set<Tag> tags = new HashSet<Tag>();
            tags.add(tag);


        return new TagCommand(userId, tags);
    }
}
