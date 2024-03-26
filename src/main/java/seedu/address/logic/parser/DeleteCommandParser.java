package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;


import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;

import seedu.address.logic.commands.DeleteCommand;

import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] indexTokens = args.trim().split("\\s+");
            List<Index> indices = new ArrayList<>();
            for (String indexToken : indexTokens) {
                Index index = ParserUtil.parseIndex(indexToken);
                indices.add(index);
            }
            return new DeleteCommand(indices);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}

