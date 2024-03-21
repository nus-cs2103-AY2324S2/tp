package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEWTIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROGRAMMING_LANGUAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.language.ProgrammingLanguage;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_COMPANY_NAME, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_INTERVIEWTIME, PREFIX_SALARY, PREFIX_INFO,
                        PREFIX_TAG, PREFIX_PROGRAMMING_LANGUAGE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(
                PREFIX_COMPANY_NAME, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_SALARY, PREFIX_INFO,
                PREFIX_ADDRESS, PREFIX_INTERVIEWTIME);

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editPersonDescriptor.setCompanyName(ParserUtil.parseCompanyName(
                    argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERVIEWTIME).isPresent()) {
            editPersonDescriptor.setDateTime(
                    ParserUtil.parseInterviewTime(argMultimap.getValue(PREFIX_INTERVIEWTIME).get()));
        }
        if (argMultimap.getValue(PREFIX_SALARY).isPresent()) {
            editPersonDescriptor.setSalary(ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get()));
        }
        if (argMultimap.getValue(PREFIX_INFO).isPresent()) {
            editPersonDescriptor.setInfo(ParserUtil.parseInfo(argMultimap.getValue(PREFIX_INFO).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);
        parseProgrammingLanguagesForEdit(argMultimap.getAllValues(PREFIX_PROGRAMMING_LANGUAGE))
                .ifPresent(editPersonDescriptor::setProgrammingLanguages);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
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
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Parses a collection of programming language names into a set of ProgrammingLanguages if the collection is
     * non-empty. If the collection contains only one element which is an empty string, it will be parsed into a
     * set containing zero programming languages.
     *
     * @param programmingLanguages A collection of programming language names to be parsed.
     * @return An Optional containing set of ProgrammingLanguages if collection is non-empty, otherwise empty Optional.
     * @throws ParseException If any of the programming language names is invalid.
     */
    private Optional<Set<ProgrammingLanguage>> parseProgrammingLanguagesForEdit(Collection<String> programmingLanguages)
            throws ParseException {
        assert programmingLanguages != null;

        if (programmingLanguages.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> languageSet = programmingLanguages.size() == 1 && programmingLanguages.contains("")
                ? Collections.emptySet() : programmingLanguages;
        return Optional.of(ParserUtil.parseProgrammingLanguages(languageSet));
    }

}
