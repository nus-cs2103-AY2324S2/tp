package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Event;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
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
     * Parses a {@code String patientHospitalId} into a {@code PatientHospitalId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code patientHospitalId} is invalid.
     */
    public static PatientHospitalId parsePatientHospitalId(String patientHospitalId) throws ParseException {
        requireNonNull(patientHospitalId);
        String trimmedId = patientHospitalId.trim();
        if (!PatientHospitalId.isValidPatientHospitalId(trimmedId)) {
            throw new ParseException(PatientHospitalId.MESSAGE_CONSTRAINTS);
        }
        return new PatientHospitalId(trimmedId);
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
     * Parses a {@code String preferredName} into a {@code PreferredName}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code preferredName} is invalid.
     */
    public static PreferredName parsePreferredName(String preferredName) throws ParseException {
        requireNonNull(preferredName);
        String trimmedPreferredName = preferredName.trim();
        if (!PreferredName.isValidPreferredName(trimmedPreferredName)) {
            throw new ParseException(PreferredName.MESSAGE_CONSTRAINTS);
        }
        return new PreferredName(trimmedPreferredName);
    }

    /**
     * Parses a {@code String food} into a {@code FoodPreference}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code food} is invalid.
     */
    public static FoodPreference parseFoodPreference(String food) throws ParseException {
        requireNonNull(food);
        String trimmedFood = food.trim();
        if (!FoodPreference.isValidFoodPreference(trimmedFood)) {
            throw new ParseException(FoodPreference.MESSAGE_CONSTRAINTS);
        }
        return new FoodPreference(trimmedFood);
    }

    /**
     * Parses a {@code String condition} into an {@code FamilyCondition}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code condition} is invalid.
     */
    public static FamilyCondition parseFamilyCondition(String condition) throws ParseException {
        requireNonNull(condition);
        String trimmedFamilyCondition = condition.trim();
        if (!FamilyCondition.isValidFamilyCondition(trimmedFamilyCondition)) {
            throw new ParseException(FamilyCondition.MESSAGE_CONSTRAINTS);
        }
        return new FamilyCondition(trimmedFamilyCondition);
    }

    /**
     * Parses a {@code String hobby} into an {@code Hobby}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code hobby} is invalid.
     */
    public static Hobby parseHobby(String hobby) throws ParseException {
        requireNonNull(hobby);
        String trimmedEmail = hobby.trim();
        if (!Hobby.isValidHobby(trimmedEmail)) {
            throw new ParseException(Hobby.MESSAGE_CONSTRAINTS);
        }
        return new Hobby(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String parsedTag = removeExtraSpaces(tag.trim().toLowerCase());
        if (!Tag.isValidTagName(parsedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        if (!Tag.isValidTagLength(parsedTag)) {
            throw new ParseException(Tag.MESSAGE_LENGTH_CONSTRAINTS);
        }
        return new Tag(parsedTag);
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
     * Removes extra spaces between words.
     *
     * @param toBeProcessed The string to be processed.
     * @return The string with extra spaces removed.
     */
    public static String removeExtraSpaces(String toBeProcessed) {
        return Arrays.stream(toBeProcessed.split("\\s+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.joining(" "));
    }

    /**
     * Parses a {@param String event} into a {@code Event}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code event} is invalid.
     */
    public static Event parseEvent(String name, String event) throws ParseException {
        String trimmedName = name.trim();
        String trimmedEventDateTimeStr = event.trim();
        requireAllNonNull(name, event);

        if (!Event.isValidEvent(trimmedEventDateTimeStr)) {
            throw new ParseException(Event.MESSAGE_CONSTRAINTS);
        }

        return new Event(trimmedName, trimmedEventDateTimeStr);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
