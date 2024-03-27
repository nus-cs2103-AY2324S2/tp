package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.UnmarkImportantCommand;
import seedu.address.logic.commands.UnmarkImportantCommand.UnmarkImportantDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.coursemate.Name;
import seedu.address.model.skill.Skill;

/**
 * Parses input arguments and creates a new MarkImportantCommand object
 */
public class UnmarkImportantCommandParser implements Parser<UnmarkImportantCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnmarkImportantCommand
     * and returns an UnmarkImportantCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkImportantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultiMap =
                ArgumentTokenizer.tokenize(args, PREFIX_SKILL);

        Name name = ParserUtil.parseName(argMultiMap.getPreamble());
        UnmarkImportantDescriptor unmarkImportantDescriptor = new UnmarkImportantDescriptor();

        parseSkillsForEdit(argMultiMap.getAllValues(PREFIX_SKILL)).ifPresent(unmarkImportantDescriptor::setSkills);

        if (!unmarkImportantDescriptor.isAnyFieldEdited()) {
            throw new ParseException(UnmarkImportantCommand.MESSAGE_NOT_EDITED);
        }
        return new UnmarkImportantCommand(name, unmarkImportantDescriptor);
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
