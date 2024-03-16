package staffconnect.logic.parser;

import static java.util.Objects.requireNonNull;
import static staffconnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
// import static staffconnect.logic.parser.CliSyntax.PREFIX_FACULTY; // TODO: add parsing for faculty and module
import static staffconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static staffconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import staffconnect.logic.commands.FilterCommand;
import staffconnect.logic.parser.exceptions.ParseException;
import staffconnect.model.person.Module;
import staffconnect.model.person.PersonHasModulePredicate;
import staffconnect.model.person.PersonHasTagsPredicate;
import staffconnect.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object.
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * FilterCommand and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE, PREFIX_TAG);

        if (!argMultimap.getValue(PREFIX_MODULE).isPresent() && !argMultimap.getValue(PREFIX_TAG).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MODULE); // TODO: update for faculty

        Module module = null;
        Set<Tag> tags = null;

        if (argMultimap.getValue(PREFIX_MODULE).isPresent()) {
            module = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE).get());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            tags = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        }

        // PersonHasFacultyPredicate facultyPredicate = new PersonHasFacultyPredicate(module);
        PersonHasModulePredicate modulePredicate = new PersonHasModulePredicate(module);
        PersonHasTagsPredicate tagsPredicate = new PersonHasTagsPredicate(tags);

        return new FilterCommand(modulePredicate, tagsPredicate);
    }

}
