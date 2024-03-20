package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREFERENCES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILLS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TERMSOFSERVICE;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Client;
import seedu.address.model.person.Department;
import seedu.address.model.person.Email;
import seedu.address.model.person.Employee;
import seedu.address.model.person.JobTitle;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Products;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Skills;
import seedu.address.model.person.Supplier;
import seedu.address.model.person.TermsOfService;
import seedu.address.model.tag.Tag;

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
                PREFIX_ADDRESS, PREFIX_TAG, PREFIX_REMARK, PREFIX_ROLE, PREFIX_PREFERENCES, PREFIX_PRODUCTS,
                PREFIX_DEPARTMENT, PREFIX_JOBTITLE, PREFIX_TERMSOFSERVICE, PREFIX_SKILLS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROLE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Remark remark = argMultimap.getValue(PREFIX_REMARK).isPresent()
                ? ParserUtil.parseRemark(argMultimap.getValue(PREFIX_REMARK).get())
                : new Remark("");
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        String role = argMultimap.getValue(PREFIX_ROLE).get();

        Person person;
        switch (role.toLowerCase()) {
        case "client":
            Optional<String> optionalPreferences = argMultimap.getValue(PREFIX_PREFERENCES);
            Optional<List<String>> optionalProducts = Optional.ofNullable(argMultimap.getAllValues(PREFIX_PRODUCTS));
            String preferences = optionalPreferences.orElse("");
            Products products = ParserUtil.parseProducts(optionalProducts.orElse(Collections.emptyList()));
            person = new Client(name, phone, email, address, remark, tagList, products, preferences);
            break;
        case "employee":
            Optional<String> optionalDepartment = argMultimap.getValue(PREFIX_DEPARTMENT);
            Optional<String> optionalJobTitle = argMultimap.getValue(PREFIX_JOBTITLE);
            Optional<List<String>> optionalSkills = Optional.ofNullable(argMultimap.getAllValues(PREFIX_SKILLS));
            Department department = optionalDepartment.isPresent() ? optionalDepartment.map(Department::new).get()
                    : new Department("-");
            JobTitle jobTitle = optionalJobTitle.isPresent() ? optionalJobTitle.map(JobTitle::new).get()
                    : new JobTitle("-");
            Skills skills = ParserUtil.parseSkills(optionalSkills.orElse(Collections.emptyList()));
            person = new Employee(name, phone, email, address, remark, tagList, department, jobTitle, skills);
            break;
        case "supplier":
            Optional<List<String>> optionalSupplierProducts = Optional.ofNullable(argMultimap
                    .getAllValues(PREFIX_PRODUCTS));
            Optional<String> optionalTermsOfService = argMultimap.getValue(PREFIX_TERMSOFSERVICE);
            Products supplierProducts = ParserUtil.parseProducts(optionalSupplierProducts
                    .orElse(Collections.emptyList()));
            TermsOfService termsOfService = optionalTermsOfService.isPresent() ? optionalTermsOfService
                    .map(TermsOfService::new).get()
                    : new TermsOfService("-");
            person = new Supplier(name, phone, email, address, remark, tagList, supplierProducts, termsOfService);
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
