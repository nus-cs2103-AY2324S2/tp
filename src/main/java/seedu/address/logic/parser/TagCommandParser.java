package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.TagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.tag.Tag;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class TagCommandParser implements Parser<TagCommand> {
    public TagCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,PREFIX_USERID,
                PREFIX_TAG);

        Id userId;
        String tag;

        userId = new Id(argMultimap.getValue(PREFIX_USERID).get());
        tag = argMultimap.getValue(PREFIX_TAG).get();

        return new TagCommand(userId, tag);
    }
}
