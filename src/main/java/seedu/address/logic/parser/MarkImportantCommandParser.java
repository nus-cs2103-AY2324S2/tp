package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.MarkImportantCommand;
import seedu.address.logic.commands.MarkImportantCommand.MarkImportantDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;



/**
 * Parses input arguments and creates a new MarkImportantCommand object
 */
public class MarkImportantCommandParser implements Parser<MarkImportantCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkImportantCommand
     * and returns an MarkImportantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkImportantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        try {
            Name name = ParserUtil.parseName(argMultiMap.getPreamble());
            MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptor();

            parseSkillsForEdit(argMultiMap.getAllValues(PREFIX_SKILL)).ifPresent(markImportantDescriptor::setSkills);

            //Will be caught by the catch clause
            if (!markImportantDescriptor.isAnyFieldEdited()) {
                throw new ParseException("");
            }
            return new MarkImportantCommand(name, markImportantDescriptor);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkImportantCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Parses {@code Collection<String> skills} into a {@code Set<Skill>} if {@code skills} is non-empty.
     * If {@code skills} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero skills.
     */
    private Optional<Set<Skill>> parseSkillsForEdit(Collection<String> skills) throws ParseException {
        assert skills != null;

        if (skills.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skills.size() == 1 && skills.contains("") ? Collections.emptySet() : skills;
        return Optional.of(ParserUtil.parseSkills(skillSet));
    }

}
