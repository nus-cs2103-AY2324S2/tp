package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.NoteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Phone;

public class NoteCommandParser implements Parser<NoteCommand> {

    public NoteCommand parse(String args) throws ParseException {
//        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_NOTE);
        Name name;
        String note;
        try {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
//            note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
            note = argMultimap.getValue(PREFIX_NOTE).orElse("");
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE), ive);
        }

        return new NoteCommand(name, note);
    }

}


