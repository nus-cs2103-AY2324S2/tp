package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_CLASS;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddClassCommand;
import seedu.address.logic.parser.exceptions.ParseException;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULE_CODE, PREFIX_TUTORIAL_CLASS);

        String module_code = argMultimap.getValue(PREFIX_MODULE_CODE).orElse("");
        String tutorial_class = argMultimap.getValue(PREFIX_TUTORIAL_CLASS).orElse("");

        return new AddClassCommand(module_code, tutorial_class);
    }
}
