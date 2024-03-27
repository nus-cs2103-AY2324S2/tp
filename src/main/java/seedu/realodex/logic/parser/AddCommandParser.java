package seedu.realodex.logic.parser;

import static seedu.realodex.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_FAMILY;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_INCOME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.realodex.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.realodex.logic.Messages;
import seedu.realodex.logic.commands.AddCommand;
import seedu.realodex.logic.parser.exceptions.ParseException;
import seedu.realodex.model.person.Address;
import seedu.realodex.model.person.Email;
import seedu.realodex.model.person.Family;
import seedu.realodex.model.person.Income;
import seedu.realodex.model.person.Name;
import seedu.realodex.model.person.Person;
import seedu.realodex.model.person.Phone;
import seedu.realodex.model.remark.Remark;
import seedu.realodex.model.tag.Tag;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME,
                                           PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_FAMILY, PREFIX_TAG, PREFIX_REMARK);

        Prefix[] listOfCompulsoryPrefixTags = argMultimap.returnListOfCompulsoryTags(PREFIX_NAME, PREFIX_ADDRESS,
                                                                                     PREFIX_INCOME, PREFIX_PHONE,
                                                                                     PREFIX_FAMILY, PREFIX_EMAIL,
                                                                                     PREFIX_TAG);
        if (!arePrefixesPresent(argMultimap,
                                listOfCompulsoryPrefixTags)) {
            String exceptionMessageForMissingPrefixes =
                    argMultimap.returnMessageOfMissingPrefixes(listOfCompulsoryPrefixTags) + "\n";
            throw new ParseException(Messages.getErrorMessageForMissingPrefixes(exceptionMessageForMissingPrefixes)
                                             + AddCommand.MESSAGE_USAGE);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INCOME, PREFIX_EMAIL,
                                                 PREFIX_FAMILY, PREFIX_ADDRESS, PREFIX_REMARK);

        StringBuilder errorMessageBuilder = new StringBuilder();
        ParserUtilResult<Name> nameStored =
                ParserUtil.parseNameReturnStored(argMultimap.getValue(PREFIX_NAME).orElseThrow());
        nameStored.buildErrorMessage(errorMessageBuilder, "name");

        ParserUtilResult<Phone> phoneStored = ParserUtil.parsePhoneReturnStored(argMultimap.getValue(PREFIX_PHONE)
                                                                                    .orElseThrow());

        phoneStored.buildErrorMessage(errorMessageBuilder, "phone");

        ParserUtilResult<Income> incomeStored =
                ParserUtil.parseIncomeReturnStored(argMultimap.getValue(PREFIX_INCOME).orElseThrow());

        incomeStored.buildErrorMessage(errorMessageBuilder, "income");

        ParserUtilResult<Email> emailStored =
                ParserUtil.parseEmailReturnStored(argMultimap.getValue(PREFIX_EMAIL).orElseThrow());

        emailStored.buildErrorMessage(errorMessageBuilder, "email");

        ParserUtilResult<Address> addressStored =
                ParserUtil.parseAddressReturnStored(argMultimap.getValue(PREFIX_ADDRESS).orElseThrow());
        addressStored.buildErrorMessage(errorMessageBuilder, "address");

        ParserUtilResult<Family> familyStored =
                ParserUtil.parseFamilyReturnStored(argMultimap.getValue(PREFIX_FAMILY).orElseThrow());
        familyStored.buildErrorMessage(errorMessageBuilder, "family");

        Set<Tag> tagList = null;
        try {
            tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        } catch (ParseException e) {
            errorMessageBuilder.append("Error parsing tags: ").append(e.getMessage()).append("\n");
        }

        Remark remark;
        remark = ParserUtil.parseRemark(argMultimap.getRemarkValue(PREFIX_REMARK).orElseThrow());

        // If any parsing operation fails, throw a ParseException with the accumulated error messages
        if (errorMessageBuilder.length() > 0) {
            throw new ParseException(errorMessageBuilder.toString().trim());
        }

        // If all parsing operations succeed, create the person object
        Name name = nameStored.returnStoredResult();
        Phone phone = phoneStored.returnStoredResult();
        Income income = incomeStored.returnStoredResult();
        Email email = emailStored.returnStoredResult();
        Address address = addressStored.returnStoredResult();
        Family family = familyStored.returnStoredResult();
        Person person = new Person(name, phone, income, email, address, family, tagList, remark);
        return new AddCommand(person);
    }

    /**
     * Checks if all specified prefixes are present in the given ArgumentMultimap.
     *
     * @param argumentMultimap the ArgumentMultimap to check
     * @param prefixes         the array of prefixes to check for
     * @return {@code true} if all specified prefixes are present in the ArgumentMultimap,
     *         {@code false} otherwise
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix[] prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
