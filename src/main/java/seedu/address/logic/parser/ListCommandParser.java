package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.ClassGroup;
import seedu.address.model.person.ListCommandPredicate;

/**
 * Parses input arguments and creates a new ListCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a ListCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (!trimmedArgs.isEmpty()) {
            String[] classGroups = trimmedArgs.split("\\s+");
            List<ClassGroup> groups;

            try {
                groups = Arrays.stream(classGroups).map(ClassGroup::new).collect(Collectors.toList());
            } catch (IllegalArgumentException err) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE)
                );
            }

            return new ListCommand(new ListCommandPredicate(Optional.of(groups)));
        } else {
            return new ListCommand(new ListCommandPredicate(Optional.empty()));
        }
    }
}
