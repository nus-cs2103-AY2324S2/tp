package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERN_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.InternDuration;
import seedu.address.model.person.InterviewDate;
import seedu.address.model.person.JobDescription;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG,
                        PREFIX_JOB_DESCRIPTION, PREFIX_INTERVIEW_DATE, PREFIX_INTERN_DURATION, PREFIX_SALARY,
                        PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG,
                PREFIX_JOB_DESCRIPTION, PREFIX_INTERN_DURATION, PREFIX_SALARY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_TAG, PREFIX_JOB_DESCRIPTION, PREFIX_INTERVIEW_DATE, PREFIX_INTERN_DURATION,
                PREFIX_SALARY, PREFIX_NOTE);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());

        Address address;
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            try {
                address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
            } catch (ParseException e) {
                throw new ParseException("Error parsing address: " + e.getMessage());
            }
        } else {
            address = new Address(""); // Provide a default empty address if not provided
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        JobDescription jobDescription = ParserUtil.parseJobDescription(
                argMultimap.getValue(PREFIX_JOB_DESCRIPTION).get());

        InterviewDate interviewDate = new InterviewDate(null);
        if (argMultimap.getValue(PREFIX_INTERVIEW_DATE).isPresent()) {
            try {
                String givenDate = argMultimap.getValue(PREFIX_INTERVIEW_DATE).get();
                if (!givenDate.isEmpty()) {
                    interviewDate = ParserUtil.parseInterviewDate(givenDate);
                }
            } catch (ParseException e) {
                throw new ParseException("Error parsing interview date: " + e.getMessage());
            }
        }

        InternDuration internDuration = ParserUtil.parseInternDuration(
                argMultimap.getValue(PREFIX_INTERN_DURATION).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());

        Note note;
        if (argMultimap.getValue(PREFIX_NOTE).isPresent()) {
            try {
                note = ParserUtil.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
            } catch (ParseException e) {
                throw new ParseException("Error parsing note: " + e.getMessage());
            }
        } else {
            note = new Note(""); // Provide a default empty note if not provided
        }

        Person person = new Person(name, phone, email, address, tag,
                jobDescription, interviewDate, internDuration, salary, note);

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
