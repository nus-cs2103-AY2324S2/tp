package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TERMSOFSERVICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Department;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.tag.Tag;
import seedu.address.model.person.TermsOfService;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_ROLE, PREFIX_PREFERENCES, PREFIX_PRODUCTS, PREFIX_DEPARTMENT,
                PREFIX_JOBTITLE, PREFIX_TERMSOFSERVICE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Remark remark = ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        String role = argMultimap.getValue(PREFIX_ROLE).get();

        Person person;
        switch (role.toLowerCase()) {
            case "client":
                String preferences = ParserUtil.parsePreferences(argMultimap.getValue(PREFIX_PREFERENCES).get());
                Products products = ParserUtil.parseProducts(argMultimap.getAllValues(PREFIX_PRODUCTS));
                person = new Client(name, phone, email, address, remark, tagList, products, preferences);
                break;
            case "employee":
                String department = argMultimap.getValue(PREFIX_DEPARTMENT).get();
                String jobTitle = argMultimap.getValue(PREFIX_JOBTITLE).get();

                person = new Employee(name, phone, email, address, remark, tagList, new Department(department),
                        new JobTitle(jobTitle), new Skills(new HashSet<>()));
                break;
            case "supplier":
                Products supplierProducts = ParserUtil.parseProducts(argMultimap.getAllValues(PREFIX_PRODUCTS));
                String termsOfService = argMultimap.getValue(PREFIX_TERMSOFSERVICE).get();
                person = new Supplier(name, phone, email, address, remark, tagList, supplierProducts,
                        new TermsOfService(termsOfService));
                break;
            default:
                throw new ParseException("Invalid role specified. Must be one of: client, employee, supplier.");
        }
        return new AddCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
