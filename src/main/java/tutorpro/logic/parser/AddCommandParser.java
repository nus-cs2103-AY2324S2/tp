package tutorpro.logic.parser;

import static tutorpro.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tutorpro.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static tutorpro.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tutorpro.logic.parser.CliSyntax.PREFIX_LEVEL;
import static tutorpro.logic.parser.CliSyntax.PREFIX_NAME;
import static tutorpro.logic.parser.CliSyntax.PREFIX_PHONE;
import static tutorpro.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static tutorpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import tutorpro.logic.commands.AddCommand;
import tutorpro.logic.parser.exceptions.ParseException;
import tutorpro.model.person.Address;
import tutorpro.model.person.Email;
import tutorpro.model.person.Name;
import tutorpro.model.person.Phone;
import tutorpro.model.person.student.Level;
import tutorpro.model.person.student.Student;
import tutorpro.model.person.student.Subject;
import tutorpro.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_LEVEL, PREFIX_SUBJECT, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_LEVEL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_LEVEL);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Level level = ParserUtil.parseLevel(argMultimap.getValue(PREFIX_LEVEL).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Subject> subjectList = ParserUtil.parseSubjects(argMultimap.getAllValues(PREFIX_SUBJECT));

        Student student = new Student(name, phone, email, address, tagList, level, subjectList);

        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
