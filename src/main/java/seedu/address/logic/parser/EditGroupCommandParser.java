package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.commands.EditGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.TelegramChat;

/**
 * Parses input arguments and creates a new {@code DeleteGroupCommand} object.
 */
public class EditGroupCommandParser implements Parser<EditGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteGroupCommand
     * and returns a DeleteGroupCommand object to execute.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_TELEGRAM);

        Name groupName = ParserUtil.parseName(argMultiMap.getPreamble());

        TelegramChat telegramChat = null;
        if (argMultiMap.getValue(PREFIX_TELEGRAM).isPresent()) {
            telegramChat = ParserUtil.parseTelegramChat(argMultiMap.getValue(PREFIX_TELEGRAM).get());
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
        }

        return new EditGroupCommand(groupName, telegramChat);
    }
}
