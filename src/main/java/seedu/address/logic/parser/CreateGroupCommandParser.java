package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.group.TelegramChat;

/**
 * Parses input arguments and creates a new {@code CreateGroupCommand} object.
 */
public class CreateGroupCommandParser implements Parser<CreateGroupCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the CreateGroupCommand
     * and returns a CreateGroupCommand object to execute.
     * @throws ParseException if the user input does not conform the expected format or the group name is used
     */
    public CreateGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE, PREFIX_TELEGRAM);

        Name name;
        try {
            name = ParserUtil.parseName(argMultiMap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateGroupCommand.MESSAGE_USAGE), pe);
        }

        Set<QueryableCourseMate> queryableCourseMateSet =
                ParserUtil.parseQueryableCourseMates(argMultiMap.getAllValues(PREFIX_COURSEMATE));

        TelegramChat telegramChat = null;
        if (argMultiMap.getValue(PREFIX_TELEGRAM).isPresent()) {
            telegramChat = ParserUtil.parseTelegramChat(argMultiMap.getValue(PREFIX_TELEGRAM).get());
        }

        return new CreateGroupCommand(name, queryableCourseMateSet, telegramChat);
    }
}
