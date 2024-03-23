package seedu.address.logic.parser;

import java.util.StringJoiner;
import java.util.function.Predicate;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Role;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    @Override
    public FilterCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_TAG,
                        CliSyntax.PREFIX_TEAM, CliSyntax.PREFIX_ROLE);

        Predicate<Employee> predicate = employee -> true;
        StringJoiner filterDescription = new StringJoiner(", ");

        if (argMultimap.getValue(CliSyntax.PREFIX_TAG).isPresent()) {
            Tag tag = ParserUtil.parseTag(argMultimap.getValue(CliSyntax.PREFIX_TAG).get());
            predicate = predicate.and(employee -> employee.getTags().contains(tag));
            filterDescription.add("Tag: " + tag);
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_TEAM).isPresent()) {
            String teamName = argMultimap.getValue(CliSyntax.PREFIX_TEAM).get().trim();
            predicate = predicate.and(employee -> employee.getTeam().toString().equalsIgnoreCase(teamName));
            filterDescription.add("Team: " + teamName);
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_ROLE).isPresent()) {
            Role role = ParserUtil.parseRole(argMultimap.getValue(CliSyntax.PREFIX_ROLE).get());
            predicate = predicate.and(employee -> employee.getRole().equals(role));
            filterDescription.add("Role: " + role);
        }

        if (argMultimap.getValue(CliSyntax.PREFIX_NAME).isPresent()) {
            String nameString = argMultimap.getValue(CliSyntax.PREFIX_NAME).get().trim();
            predicate = predicate.and(employee -> employee.getName().fullName.equalsIgnoreCase(nameString));
            filterDescription.add("Name: " + nameString);
        }

        return new FilterCommand(predicate, filterDescription.toString());
    }
}
