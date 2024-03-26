package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PROFILE_PICTURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TECH_STACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditContactDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.techstack.TechStack;

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
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                        PREFIX_GITHUB_USERNAME, PREFIX_PROFILE_PICTURE, PREFIX_TECH_STACK, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_GITHUB_USERNAME, PREFIX_PROFILE_PICTURE);

        EditContactDescriptor editContactDescriptor = new EditContactDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editContactDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editContactDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editContactDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editContactDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        if (argMultimap.getValue(PREFIX_GITHUB_USERNAME).isPresent()) {
            editContactDescriptor.setGitHubUsername(
                    ParserUtil.parseGitHubUsername(argMultimap.getValue(PREFIX_GITHUB_USERNAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PROFILE_PICTURE).isPresent()) {
            editContactDescriptor.setProfilePicture(ParserUtil.parseProfilePicture(argMultimap.
                    getValue(PREFIX_PROFILE_PICTURE).get()));
        }
        parseTechStackForEdit(argMultimap.getAllValues(PREFIX_TECH_STACK))
                .ifPresent(editContactDescriptor::setTechStack);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editContactDescriptor::setTags);

        if (!editContactDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editContactDescriptor);
    }

    /**
     * Parses {@code Collection<String> tech stack} into a {@code Set<TechStack>} if {@code techStack} is non-empty.
     * If {@code techStack} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<TechStack>} containing zero tech stack.
     */
    private Optional<Set<TechStack>> parseTechStackForEdit(Collection<String> techStack) throws ParseException {
        assert techStack != null;

        if (techStack.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> techStackSet = techStack.size() == 1 && techStack.contains("") ? Collections.emptySet() :
                techStack;
        return Optional.of(ParserUtil.parseTechStacks(techStackSet));
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

}
