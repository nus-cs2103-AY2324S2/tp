package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;

import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Module;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new {@code RemarkCommand} object
 */
public class AddClassCommandParser implements Parser<AddClassCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code RemarkCommand}
     * and returns a {@code RemarkCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddClassCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_TUTORIALCLASS);

        String moduleCode = argMultimap.getValue(PREFIX_MODULECODE).orElse("");
        String tutorialClass = argMultimap.getValue(PREFIX_TUTORIALCLASS).orElse("");

        return new AddClassCommand(new Module(new Name(moduleCode), new TutorialClass(tutorialClass)));
    }
}
