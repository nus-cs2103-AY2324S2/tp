package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ARTICLETAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OUTLET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.articlecommands.EditArticleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.Article;
import seedu.address.model.article.Author;
import seedu.address.model.article.Outlet;
import seedu.address.model.article.Source;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditArticleCommand object
 */
public class EditArticleCommandParser implements Parser<EditArticleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditArticleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_SOURCE,
                        PREFIX_ARTICLETAG, PREFIX_OUTLET, PREFIX_PUBLICATION_DATE, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditArticleCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_PUBLICATION_DATE, PREFIX_STATUS);

        EditArticleCommand.EditArticleDescriptor editArticleDescriptor = new EditArticleCommand.EditArticleDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editArticleDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PUBLICATION_DATE).isPresent()) {
            editArticleDescriptor.setPublicationDate(ParserUtil.parsePublicationDate(argMultimap
                    .getValue(PREFIX_PUBLICATION_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editArticleDescriptor.setStatus((Article.Status) ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS)
                    .get()));
        }

        parseAuthorsForEdit(argMultimap.getAllValues(PREFIX_AUTHOR)).ifPresent(editArticleDescriptor::setAuthors);
        parseSourcesForEdit(argMultimap.getAllValues(PREFIX_SOURCE)).ifPresent(editArticleDescriptor::setSources);
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_ARTICLETAG)).ifPresent(editArticleDescriptor::setTags);
        parseOutletsForEdit(argMultimap.getAllValues(PREFIX_OUTLET)).ifPresent(editArticleDescriptor::setOutlets);

        if (!editArticleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditArticleCommand(index, editArticleDescriptor);
    }

    private Optional<Set<Author>> parseAuthorsForEdit(Collection<String> authors) throws ParseException {
        assert authors != null;

        if (authors.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> authorSet = authors.size() == 1 && authors.contains("") ? Collections.emptySet() : authors;
        return Optional.of(ParserUtil.parseAuthors(authorSet));
    }

    private Optional<Set<Source>> parseSourcesForEdit(Collection<String> sources) throws ParseException {
        assert sources != null;

        if (sources.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> sourceSet = sources.size() == 1 && sources.contains("") ? Collections.emptySet() : sources;
        return Optional.of(ParserUtil.parseSources(sourceSet));
    }

    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
    private Optional<Set<Outlet>> parseOutletsForEdit(Collection<String> outlets) throws ParseException {
        assert outlets != null;

        if (outlets.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> outletSet = outlets.size() == 1 && outlets.contains("") ? Collections.emptySet() : outlets;
        return Optional.of(ParserUtil.parseOutlets(outletSet));
    }

}
