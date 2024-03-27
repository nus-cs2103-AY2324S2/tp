package seedu.internhub.logic.parser;

import static seedu.internhub.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.internhub.logic.commands.AddCommand;
import seedu.internhub.logic.parser.exceptions.ParseException;
import seedu.internhub.model.person.Address;
import seedu.internhub.model.person.Email;
import seedu.internhub.model.person.InternDuration;
import seedu.internhub.model.person.InterviewDate;
import seedu.internhub.model.person.JobDescription;
import seedu.internhub.model.person.Name;
import seedu.internhub.model.person.Note;
import seedu.internhub.model.person.Person;
import seedu.internhub.model.person.Phone;
import seedu.internhub.model.person.Salary;
import seedu.internhub.model.person.Tag;

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
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_JOB_DESCRIPTION,
                        CliSyntax.PREFIX_INTERVIEW_DATE, CliSyntax.PREFIX_INTERN_DURATION,
                        CliSyntax.PREFIX_SALARY, CliSyntax.PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_JOB_DESCRIPTION, CliSyntax.PREFIX_INTERN_DURATION,
                CliSyntax.PREFIX_SALARY) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_TAG, CliSyntax.PREFIX_JOB_DESCRIPTION,
                CliSyntax.PREFIX_INTERVIEW_DATE, CliSyntax.PREFIX_INTERN_DURATION,
                CliSyntax.PREFIX_SALARY, CliSyntax.PREFIX_NOTE);
        Name name = ParserUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());

        Address address;
        if (argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).isPresent()) {
            try {
                address = ParserUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());
            } catch (ParseException e) {
                throw new ParseException("Error parsing address: " + e.getMessage());
            }
        } else {
            address = new Address(""); // Provide a default empty address if not provided
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(CliSyntax.PREFIX_TAG).get());
        JobDescription jobDescription = ParserUtil.parseJobDescription(
                argMultimap.getValue(CliSyntax.PREFIX_JOB_DESCRIPTION).get());

        InterviewDate interviewDate = new InterviewDate(null);
        if (argMultimap.getValue(CliSyntax.PREFIX_INTERVIEW_DATE).isPresent()) {
            try {
                String givenDate = argMultimap.getValue(CliSyntax.PREFIX_INTERVIEW_DATE).get();
                if (!givenDate.isEmpty()) {
                    interviewDate = ParserUtil.parseInterviewDate(givenDate);
                }
            } catch (ParseException e) {
                throw new ParseException("Error parsing interview date: " + e.getMessage());
            }
        }

        InternDuration internDuration = ParserUtil.parseInternDuration(
                argMultimap.getValue(CliSyntax.PREFIX_INTERN_DURATION).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(CliSyntax.PREFIX_SALARY).get());

        Note note;
        if (argMultimap.getValue(CliSyntax.PREFIX_NOTE).isPresent()) {
            try {
                note = ParserUtil.parseNote(argMultimap.getValue(CliSyntax.PREFIX_NOTE).get());
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
