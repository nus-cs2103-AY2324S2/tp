package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AdmissionDate;
import seedu.address.model.person.Dob;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Ward;
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
     * Parses a {@code String dob} into a {@code Dob}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dob} is invalid.
     */
    public static Dob parseDob(String dob) throws ParseException {
        requireNonNull(dob);
        String trimmedDob = dob.trim();
        if (!Dob.isValidDob(trimmedDob)) {
            throw new ParseException(Dob.MESSAGE_CONSTRAINTS);
        }
        return new Dob(trimmedDob);
    }

    /**
     * Parses a {@code String ic} into a {@code Ic}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ic} is invalid.
     */
    public static Ic parseIc(String ic) throws ParseException {
        requireNonNull(ic);
        // Todo: Error handling
        String trimmedIc = ic.trim();
        if (!Ic.isValidIc(trimmedIc)) {
            throw new ParseException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(trimmedIc);
    }

    /**
     * Parses a {@code String admissionDate} into a {@code AdmissionDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code admissionDate} is invalid.
     */
    public static AdmissionDate parseAdmissionDate(String admissionDate) throws ParseException {
        requireNonNull(admissionDate);
        String trimmedAdmissionDate = admissionDate.trim();
        if (!AdmissionDate.isValidAdmissionDate(admissionDate.trim())) {
            throw new ParseException(AdmissionDate.MESSAGE_CONSTRAINTS);
        }
        return new AdmissionDate(trimmedAdmissionDate);
    }

    /**
     * Parses a {@code String ward} into a {@code Ward}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ward} is invalid.
     */
    public static Ward parseWard(String ward) throws ParseException {
        requireNonNull(ward);
        String trimmedWard = ward.trim();
        if (!Ward.isValidWard(trimmedWard)) {
            throw new ParseException(Ward.MESSAGE_CONSTRAINTS);
        }
        // For now we assume all strings are valid wards.
        // We can change this to be better in the future.
        return new Ward(trimmedWard);
    }

}
