package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUNDING_STAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDUSTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
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
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                  PREFIX_INDUSTRY, PREFIX_FUNDING_STAGE, PREFIX_PHONE,
                  PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDUSTRY,
                PREFIX_FUNDING_STAGE, PREFIX_ADDRESS,
                PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_FUNDING_STAGE, PREFIX_INDUSTRY);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        FundingStage fundingStage = ParserUtil.parseFundingStage(argMultimap.getValue(PREFIX_FUNDING_STAGE).get());
        Industry industry = ParserUtil.parseIndustry(argMultimap.getValue(PREFIX_INDUSTRY).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Startup startup = new Startup(name, fundingStage, industry, phone, email, address, tagList);

        return new AddCommand(startup);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
