package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIMETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROOMNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.FreeTimeTag;

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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOMNUMBER,
                        PREFIX_TELEGRAM, PREFIX_BIRTHDAY, PREFIX_FREETIMETAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOMNUMBER,
                PREFIX_TELEGRAM);

        // Mandatory fields
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        // Optional fields
        Optional<String> emailText = argMultimap.getValue(PREFIX_EMAIL);
        Email email = emailText.isPresent() ? ParserUtil.parseEmail(emailText.get()) : null;

        Optional<String> roomNumberText = argMultimap.getValue(PREFIX_ROOMNUMBER);
        RoomNumber roomNumber = roomNumberText.isPresent() ? ParserUtil.parseRoomNumber(roomNumberText.get()) : null;

        Optional<String> telegramText = argMultimap.getValue(PREFIX_TELEGRAM);
        Telegram telegram = telegramText.isPresent() ? ParserUtil.parseTelegram(telegramText.get()) : null;

        Optional<String> birthdayText = argMultimap.getValue(PREFIX_BIRTHDAY);
        Birthday birthday = birthdayText.isPresent() ? ParserUtil.parseBirthday(birthdayText.get()) : null;

        Optional<String> freeTimeTagText = argMultimap.getValue(PREFIX_FREETIMETAG);
        Set<FreeTimeTag> freeTimeTags = ParserUtil.parseFreeTimeTags(argMultimap.getAllValues(PREFIX_FREETIMETAG));

        Person person = new Person(name, phone, email, roomNumber, telegram, birthday, freeTimeTags);
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
