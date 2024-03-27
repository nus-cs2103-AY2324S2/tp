package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Set;

import seedu.address.logic.commands.UnrequireSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new {@code UnrequireSkillCommand} object
 */
public class UnrequireSkillCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UnrequireSkillCommand}
     * and returns a {@code UnrequireSkillCommand} object to execute.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnrequireSkillCommand parse(String args) throws ParseException {
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
            return new UnrequireSkillCommand(name, skillSet);
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnrequireSkillCommand.MESSAGE_USAGE));
        }
    }
}
