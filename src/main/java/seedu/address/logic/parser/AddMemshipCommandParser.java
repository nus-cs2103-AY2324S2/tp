package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEMSHIP;

import seedu.address.logic.commands.AddMemshipCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Membership;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code Membership Command} object
 */
public class AddMemshipCommandParser implements Parser<AddMemshipCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMemshipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MEMSHIP);

        Name name;
        try {
            name = ParserUtil.parseName(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE);
        }

        String mship = argMultimap.getValue(PREFIX_MEMSHIP).orElse("");
        if (!Membership.isValidMembership(mship) || mship.isBlank()) {
            throw new ParseException(Membership.MESSAGE_CONSTRAINTS + "\n" + AddMemshipCommand.MESSAGE_USAGE);
        }

        return new AddMemshipCommand(name, mship);
    }
}
