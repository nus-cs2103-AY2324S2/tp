package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.logic.commands.EditExamCommand.EditExamDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditExamCommand object
 */
public class EditExamCommandParser implements Parser<EditExamCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditExamCommand
     * and returns an EditExamCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditExamCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_SCORE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExamCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_SCORE);

        EditExamDescriptor editExamDescriptor = new EditExamDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editExamDescriptor.setName(ParserUtil.parseExamName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_SCORE).isPresent()) {
            editExamDescriptor.setMaxScore(ParserUtil.parseExamScore(argMultimap.getValue(PREFIX_SCORE).get()));
        }

        if (!editExamDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditExamCommand.MESSAGE_NOT_EDITED);
        }

        return new EditExamCommand(index, editExamDescriptor);
    }

}
