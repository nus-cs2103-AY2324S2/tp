package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddDoctorCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.doctor.DoB;
import seedu.address.model.doctor.Doctor;
import seedu.address.model.doctor.NRIC;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Set;
import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class AddDoctorParser implements Parser<AddDoctorCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddDoctorCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NRIC, PREFIX_NAME, PREFIX_DOB, PREFIX_PHONE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NRIC, PREFIX_NAME, PREFIX_DOB, PREFIX_PHONE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NRIC, PREFIX_NAME, PREFIX_DOB, PREFIX_PHONE);
        NRIC nric = ParserUtil.parseNRIC(argMultimap.getValue(PREFIX_NRIC).get());
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        DoB dob = ParserUtil.parseDOB(argMultimap.getValue(PREFIX_DOB).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());

        Doctor doctor = new Doctor(nric, name, dob, phone);

        return new AddDoctorCommand(doctor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}