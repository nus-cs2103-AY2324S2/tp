package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Set;

import seedu.address.logic.commands.RequireSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new {@code RequireSkillCommand} object
 */
public class RequireSkillCommandParser implements Parser<RequireSkillCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code RequireSkillCommand}
     * and returns a {@code RequireSkillCommand} object to execute.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RequireSkillCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        try {
            Name name = ParserUtil.parseName(argMultiMap.getPreamble());
            Set<Skill> skillSet =
                    ParserUtil.parseSkills(argMultiMap.getAllValues(PREFIX_SKILL));

            // will get caught by the catch clause, leave empty.
            if (skillSet.isEmpty()) {
                throw new ParseException("");
            }
            return new RequireSkillCommand(name, skillSet);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RequireSkillCommand.MESSAGE_USAGE));
        }
    }
}
