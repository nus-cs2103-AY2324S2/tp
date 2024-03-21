package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AUTHOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PUBLICATION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import seedu.address.logic.commands.articlecommands.AddArticleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.Article;

/**
 * Parses input arguments and creates a new AddArticleCommand object
 */
public class AddArticleCommandParser implements Parser<AddArticleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddArticleCommand
     * and returns an AddArticleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddArticleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_PUBLICATION_DATE, PREFIX_SOURCE,
                        PREFIX_CATEGORY, PREFIX_STATUS);
        //Temporarily reinstate source requirement
        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_AUTHOR, PREFIX_PUBLICATION_DATE, PREFIX_SOURCE,
                PREFIX_CATEGORY, PREFIX_STATUS) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddArticleCommand.MESSAGE_USAGE));
        }

        String title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        String[] authorList = ParserUtil.parseAuthors(argMultimap.getAllValues(PREFIX_AUTHOR));
        LocalDateTime publicationDate = ParserUtil.parsePublicationDate(argMultimap.getValue(PREFIX_PUBLICATION_DATE)
                .get());
        String[] sourceList = ParserUtil.parseSources(argMultimap.getAllValues(PREFIX_SOURCE));
        String category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
        Article.Status status = (Article.Status) ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get());

        Article article = new Article(title, authorList, publicationDate, sourceList, category, status);

        return new AddArticleCommand(article);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
