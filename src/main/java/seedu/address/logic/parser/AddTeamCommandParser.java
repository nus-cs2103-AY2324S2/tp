package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULECODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM_SIZE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;

import java.util.stream.Stream;

import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.TutorialClass;
import seedu.address.model.module.TutorialTeam;

/**
 * Parses input arguments and creates a new {@code AddClassCommandParser} object
 */
public class AddTeamCommandParser implements Parser<AddTeamCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the
     * {@code AddClassCommandParser}
     * and returns a {@code AddClassCommandParser} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddTeamCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_MODULECODE, PREFIX_TUTORIALCLASS,
                PREFIX_NAME, PREFIX_TEAM_SIZE);

        if (!arePrefixesPresent(argMultimap, PREFIX_MODULECODE, PREFIX_TUTORIALCLASS, PREFIX_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE));
        }

        boolean isTeamSizePresent = argMultimap.getValue(PREFIX_TEAM_SIZE).isPresent();
        ModuleCode moduleCode = ParserUtil.parseModuleCode(argMultimap.getValue(PREFIX_MODULECODE).get());
        TutorialClass tutorialClass = ParserUtil.parseTutorialClass(argMultimap.getValue(PREFIX_TUTORIALCLASS).get());
        String teamName = argMultimap.getValue(PREFIX_NAME).orElse("");

        if (!TutorialTeam.isValidTeamName(teamName)) {
            throw new ParseException(TutorialTeam.MESSAGE_NAME_CONSTRAINTS);
        }

        if (isTeamSizePresent) {
            int teamSize;
            try {
                teamSize = Integer.parseInt(argMultimap.getValue(PREFIX_TEAM_SIZE).get());
            } catch (NumberFormatException e) {
                throw new ParseException(TutorialTeam.MESSAGE_SIZE_CONSTRAINTS);
            }
            if (teamSize <= 0) {
                throw new ParseException(TutorialTeam.MESSAGE_SIZE_CONSTRAINTS);
            }
            return new AddTeamCommand(moduleCode, tutorialClass, teamName, teamSize);
        } else {
            return new AddTeamCommand(moduleCode, tutorialClass, teamName);
        }
    }

    /**
     * Returns true if all the prefixes are present in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
