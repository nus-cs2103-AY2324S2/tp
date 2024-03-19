package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.article.Article.Status.ARCHIVED;
import static seedu.address.model.article.Article.Status.DRAFT;
import static seedu.address.model.article.Article.Status.PUBLISHED;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.article.Article;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String title} into a {@code Title}.
     */
    public static String parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedTitle = title.trim();
        //removed the check for author validity
        return trimmedTitle;
    }

    /**
     * Parses a {@code String author} into a {@code Author}.
     */
    public static String parseAuthor(String author) throws ParseException {
        requireNonNull(author);
        String trimmedAuthor = author.trim();
        //removed the check for author validity
        return trimmedAuthor;
    }

    /**
     * Parses a {@code List<String> authors} into a {@code String[]}.
     */
    public static String[] parseAuthors(List<String> authors) throws ParseException {
        requireNonNull(authors);
        final String[] authorSet = new String[authors.size()];
        for (int i = 0; i < authors.size(); i++) {
            authorSet[i] = parseAuthor(authors.get(i));
        }
        return authorSet;
    }

    /**
     * Parses a {@code String publicationDate} into a {@code LocalDateTime}.
     */
    public static LocalDateTime parsePublicationDate(String publicationDate) throws ParseException {
        requireNonNull(publicationDate);
        String trimmedPublicationDate = publicationDate.trim();
        LocalDateTime parsedDate = LocalDateTime.parse(trimmedPublicationDate);
        //removed the check for publication date validity
        return parsedDate;
    }

    /**
     * Parses a {@code String source} into a {@code Source}.
     */
    public static String parseSource(String source) throws ParseException {
        requireNonNull(source);
        String trimmedSource = source.trim();
        //removed the check for Source validity
        return trimmedSource;
    }

    /**
     * Parses a {@code List<String> sources} into a {@code String[]}.
     */
    public static String[] parseSources(List<String> sources) throws ParseException {
        requireNonNull(sources);
        final String[] sourceSet = new String[sources.size()];
        for (int i = 0; i < sources.size(); i++) {
            sourceSet[i] = parseSource(sources.get(i));
        }
        return sourceSet;
    }

    /**
     * Parses a {@code String category} into a {@code Category}.
     */
    public static String parseCategory(String category) throws ParseException {
        requireNonNull(category);
        String trimmedCategory = category.trim();
        //removed the check for category validity
        return trimmedCategory;
    }

    /**
     * Parses a {@code String status} into a {@code Status}.
     */
    public static Enum<Article.Status> parseStatus(String status) throws ParseException {
        requireNonNull(status);
        String trimmedStatus = status.trim();
        switch (trimmedStatus) {
        case "DRAFT":
            return DRAFT;
        case "PUBLISHED":
            return PUBLISHED;
        case "ARCHIVED":
            return ARCHIVED;
        default:
            throw new ParseException("Invalid status");
        }
    }
}
