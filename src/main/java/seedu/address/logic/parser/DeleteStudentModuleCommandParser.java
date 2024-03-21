package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentModuleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

/**
 * Parses input arguments and creates a new DeleteStudentModuleCommand object
 */
public class DeleteStudentModuleCommandParser implements Parser<DeleteStudentModuleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteStudentModuleCommand
     * and returns an DeleteStudentModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteStudentModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_ID, PREFIX_MODULE_CODE);
        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_ID, PREFIX_MODULE_CODE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteStudentModuleCommand.MESSAGE_USAGE
            ));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_MODULE_CODE);
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        ModuleCode moduleCode = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE_CODE).get());

        return new DeleteStudentModuleCommand(index, moduleCode);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
