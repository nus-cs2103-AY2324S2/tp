package seedu.address.logic.parser;

import static seedu.address.logic.messages.Messages.MESSAGE_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_INVALID_FIELD_FORMAT;
import static seedu.address.logic.messages.Messages.MESSAGE_UNDETECTED_FIELD_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Rating;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArrayList<String> unknownPrefixes = ArgumentTokenizer.checkUnknownPrefix(args,
                PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE, PREFIX_RATING);

        if (unknownPrefixes.size() > 0) {
            String exception = String.format(MESSAGE_INVALID_FIELD_FORMAT, unknownPrefixes);
            exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
            throw new ParseException(exception);
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_NOTE,
                        PREFIX_RATING);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)) {
            ArrayList<String> undetectedFields = ArgumentTokenizer.checkUndetectedPrefix(argMultimap,
                    PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL);
            String exception = String.format(MESSAGE_UNDETECTED_FIELD_FORMAT, undetectedFields);
            exception += "\n" + String.format(MESSAGE_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
            throw new ParseException(exception);
        }

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        String noteContent = argMultimap.getValue(PREFIX_NOTE).orElse("No note here");
        Note note = noteContent.equals("No note here") ? new Note(noteContent) : ParserUtil.parseNote(noteContent);
        Rating rating = ParserUtil.parseRating(argMultimap.getValue(PREFIX_RATING).orElse("0"));
        Tag tag = new Tag("other");
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        Person person = new Person(name, phone, email, address, note, tags, rating);

        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
