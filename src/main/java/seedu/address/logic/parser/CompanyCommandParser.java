package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;

import seedu.address.logic.commands.CompanyCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Company;

/**
 * Parses input arguments and creates a new {@code CompanyCommand} object
 */
public class CompanyCommandParser implements Parser<CompanyCommand>{

    public CompanyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMPANY);

        String contactName;
        contactName = argMultimap.getPreamble();

        String company = argMultimap.getValue(PREFIX_COMPANY).orElse("");

        return new CompanyCommand(contactName, new Company(company));
    }


}