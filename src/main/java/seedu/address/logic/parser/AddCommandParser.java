package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.TelegramHandle;
import seedu.address.model.skill.Skill;

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
                ArgumentTokenizer.tokenize(args, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_SKILL);

        try {
            argMultimap = setPreambleAsName(argMultimap);
        } catch (ParseException e) {
            throw e;
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Set<Skill> skillList = ParserUtil.parseSkills(argMultimap.getAllValues(PREFIX_SKILL));

        TelegramHandle telegramHandle = null;
        if (argMultimap.getValue(PREFIX_TELEGRAM).isPresent()) {
            telegramHandle = ParserUtil.parseTelegramHandle(argMultimap.getValue(PREFIX_TELEGRAM).get());
        }

        CourseMate courseMate = new CourseMate(name, phone, email, telegramHandle, skillList);

        return new AddCommand(courseMate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Isolates the preamble of the {@code argsString} and set is as the name argument of the command.
     *
     * @param argsString   Arguments string of the form: {@code preamble <prefix> value <prefix> value ...}
     * @param argMultimap  ArgumentMultimap object that maps prefixes to their arguments
     * @return             ArgumentMultimap object that maps the name prefix to the name argument
     */
    private static ArgumentMultimap setPreambleAsName(ArgumentMultimap argMultimap)
            throws ParseException {
        String name = argMultimap.getPreamble();
        if (name.isEmpty() || !Name.isValidName(name)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        argMultimap.put(PREFIX_NAME, name);
        return argMultimap;
    }
}
