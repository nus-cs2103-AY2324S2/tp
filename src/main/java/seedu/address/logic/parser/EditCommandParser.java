package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_EMAIL;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_GRADE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_NAME;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_PHONE;
import static seedu.address.logic.parser.CliSyntax.OPTION_PRINT_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_EDIT;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.grade.Grade;
import seedu.address.model.tag.Tag;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME_EDIT, PREFIX_PHONE_EDIT,
                PREFIX_EMAIL_EDIT, PREFIX_ADDRESS_EDIT, PREFIX_TAG_EDIT, PREFIX_GRADE_EDIT,
                OPTION_PRINT_NAME, OPTION_PRINT_PHONE, OPTION_PRINT_EMAIL,
                OPTION_PRINT_ADDRESS, OPTION_PRINT_TAG, OPTION_PRINT_GRADE);

        // Check if any option to print is requested
        if (argMultimap.getValue(OPTION_PRINT_NAME).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    true, false, false,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_PHONE).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    false, true, false,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_EMAIL).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    false, false, true,
                    false, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_ADDRESS).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    false, false,
                    false, true, false, false);
        } else if (argMultimap.getValue(OPTION_PRINT_TAG).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    false, false, false,
                    false, true, false);
        } else if (argMultimap.getValue(OPTION_PRINT_GRADE).isPresent()) {
            Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
            return new EditCommand(index, new EditCommand.EditPersonDescriptor(),
                    false, false, false,
                    false, false, true);
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME_EDIT, PREFIX_PHONE_EDIT,
                PREFIX_EMAIL_EDIT, PREFIX_ADDRESS_EDIT, PREFIX_TAG_EDIT, PREFIX_GRADE_EDIT);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME_EDIT).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE_EDIT).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL_EDIT).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL_EDIT).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS_EDIT).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS_EDIT).get()));
        }

        // Check if PREFIX_TAG_EDIT is present before parsing multiple tags
        if (argMultimap.getValue(PREFIX_TAG_EDIT).isPresent()) {
            parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG_EDIT))
                    .ifPresent(editPersonDescriptor::setTags);
        }

        // Check if PREFIX_GRADE_EDIT is present before parsing multiple grades
        if (argMultimap.getValue(PREFIX_GRADE_EDIT).isPresent()) {
            parseGradesForEdit(argMultimap.getAllValues(PREFIX_GRADE_EDIT))
                    .ifPresent(editPersonDescriptor::setGrades);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }
        if (!editPersonDescriptor.isSingleFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_EDITED_BUT_MORE_THAN_ONE);
        }

        return new EditCommand(index, editPersonDescriptor, false, false, false, false, false, false);
    }


    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }

        Set<Tag> tagSet = new HashSet<>();
        for (String tagString : tags) {
            // Remove leading and trailing whitespace
            String trimmedTagString = tagString.trim();
            // Remove leading and trailing curly braces if present
            if (trimmedTagString.startsWith("[[") && trimmedTagString.endsWith("]]")) {
                trimmedTagString = trimmedTagString.substring(2, trimmedTagString.length() - 2);
            }
            // Split by commas to handle multiple tags
            String[] tagArray = trimmedTagString.split(",");
            for (String tag : tagArray) {
                tagSet.add(new Tag(tag.trim()));
            }
        }

        return Optional.of(tagSet);
    }


    /**
     * Parses {@code Collection<String> grades} into a {@code Set<Grade>} if {@code grades} is non-empty.
     * If {@code grades} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Grade>} containing zero grades.
     */
    private Optional<Set<Grade>> parseGradesForEdit(Collection<String> grades) throws ParseException {
        assert grades != null;

        if (grades.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> gradeSet = grades.size() == 1 && grades.contains("") ? Collections.emptySet() : grades;
        return Optional.of(ParserUtil.parseGrades(gradeSet));
    }
}
