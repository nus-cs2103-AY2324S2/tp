package seedu.address.logic.parser;

import java.util.Set;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COURSEMATE;

import seedu.address.logic.commands.CreateGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new {@code CreateGroupCommand} object.
 */
public class CreateGroupCommandParser implements Parser<CreateGroupCommand> {
    public CreateGroupCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_COURSEMATE);

        Name name = ParserUtil.parseName(argMultiMap.getPreamble());
        Set<CourseMate> courseMateList = ParserUtil.parseCourseMates(argMultiMap.getAllValues(PREFIX_COURSEMATE));

        // TODO: check if group already exists

        return new CreateGroupCommand(name, courseMateList);
    }
}
