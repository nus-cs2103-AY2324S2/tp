package seedu.teachstack.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.teachstack.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.teachstack.logic.parser.CliSyntax.PREFIX_STUDENTID;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.teachstack.logic.commands.EditCommand;
import seedu.teachstack.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.teachstack.logic.parser.exceptions.ParseException;
import seedu.teachstack.model.group.Group;
import seedu.teachstack.model.person.StudentId;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_EMAIL,
                        PREFIX_GRADE, PREFIX_GROUP);

        StudentId id;

        try {
            id = ParserUtil.parseStudentId(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_STUDENTID, PREFIX_EMAIL,
                PREFIX_GRADE);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            editPersonDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_GRADE).isPresent()) {
            editPersonDescriptor.setGrade(ParserUtil.parseGrade(argMultimap.getValue(PREFIX_GRADE).get()));
        }

        parseGroupsForEdit(argMultimap.getAllValues(PREFIX_GROUP)).ifPresent(editPersonDescriptor::setGroups);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(id, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> groups} into a {@code Set<Group>} if {@code groups} is non-empty.
     * If {@code groups} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Group>} containing zero groups.
     */
    private Optional<Set<Group>> parseGroupsForEdit(Collection<String> groups) throws ParseException {
        assert groups != null;

        if (groups.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> groupSet = groups.size() == 1 && groups.contains("") ? Collections.emptySet() : groups;
        return Optional.of(ParserUtil.parseGroups(groupSet));
    }

}
