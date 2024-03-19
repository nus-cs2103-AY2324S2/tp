package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.articlecommands.EditArticleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.Article;

/**
 * Parses input arguments and creates a new EditArticleCommand object
 */
public class EditArticleCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditArticleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_PUBLICATION_DATE, PREFIX_SOURCE,
                        PREFIX_CATEGORY, PREFIX_STATUS);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditArticleCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_PUBLICATION_DATE, PREFIX_SOURCE,
                PREFIX_CATEGORY, PREFIX_STATUS);

        EditArticleCommand.EditArticleDescriptor editArticleDescriptor = new EditArticleCommand.EditArticleDescriptor();

        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editArticleDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_PUBLICATION_DATE).isPresent()) {
            editArticleDescriptor.setPublicationDate(ParserUtil.parsePublicationDate(argMultimap.getValue(PREFIX_PUBLICATION_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_CATEGORY).isPresent()) {
            editArticleDescriptor.setCategory(ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editArticleDescriptor.setStatus((Article.Status) ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }

        parseAuthorsForEdit(argMultimap.getAllValues(PREFIX_AUTHOR)).ifPresent(editArticleDescriptor::setAuthors);
        parseSourcesForEdit(argMultimap.getAllValues(PREFIX_SOURCE)).ifPresent(editArticleDescriptor::setSource);

        if (!editArticleDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditArticleCommand(index, editArticleDescriptor);
    }

    private Optional<String[]> parseAuthorsForEdit(List<String> authors) throws ParseException {
        assert authors != null;

        for (String author : authors) {
            if (author.isEmpty()) {
                return Optional.empty();
            }
        }
        List<String> authorSet = authors;
        authorSet = authorSet.size() == 1 && authorSet.contains("") ? Collections.emptyList() : authorSet;
        return Optional.of(ParserUtil.parseAuthors(authorSet));
    }

    private Optional<String[]> parseSourcesForEdit(List<String> sources) throws ParseException {
        assert sources != null;

        for (String Source : sources) {
            if (Source.isEmpty()) {
                return Optional.empty();
            }
        }
        List<String> SourceSet = sources;
        SourceSet = SourceSet.size() == 1 && SourceSet.contains("") ? Collections.emptyList() : SourceSet;
        return Optional.of(ParserUtil.parseSources(SourceSet));
    }

}

