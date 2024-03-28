package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddMemPointsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.allergen.Allergen;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.MembershipPoints;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Points;
import seedu.address.model.person.orders.Order;

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
     * Parses a {@code String membershipPts} into a {@code MembershipPoints}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code membershipPts} is invalid.
     */
    public static int parseMemPointsToAdd(String pointsToAdd) throws ParseException {
        requireNonNull(pointsToAdd);

        int parsedPointsToAdd;
        try {
            parsedPointsToAdd = Integer.parseInt(pointsToAdd);
        } catch (NumberFormatException e) {
            throw new ParseException(AddMemPointsCommand.MESSAGE_CONSTRAINTS);
        }

        if (!MembershipPoints.isValidMembershipPoints(parsedPointsToAdd)) {
            throw new ParseException(AddMemPointsCommand.MESSAGE_CONSTRAINTS);
        }

        return parsedPointsToAdd;
    }

    /**
     * Parses a {@code String allergen} into a {@code Allergen}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code allergen} is invalid.
     */
    public static Allergen parseAllergen(String allergen) throws ParseException {
        requireNonNull(allergen);
        String trimmedAllergen = allergen.trim();
        if (!Allergen.isValidAllergenName(trimmedAllergen)) {
            throw new ParseException(Allergen.MESSAGE_CONSTRAINTS);
        }
        return new Allergen(trimmedAllergen);
    }

    /**
     * Parses {@code Collection<String> allergens} into a {@code Set<Allergen>}.
     */
    public static Set<Allergen> parseAllergens(Collection<String> allergens) throws ParseException {
        requireNonNull(allergens);
        final Set<Allergen> allergenSet = new HashSet<>();
        for (String allergenName : allergens) {
            allergenSet.add(parseAllergen(allergenName));
        }
        return allergenSet;
    }

    /**
     * Parses a {@code String points} into a {@code Points}.
     * Leading and trailing whitespaces will be trimmed.
     * @throws ParseException if the given {@code points} is invalid (not a non-negative integer).
     */
    public static Points parsePoints(String points) throws ParseException {
        requireNonNull(points);
        String trimmedPoints = points.trim();
        if (!Points.isValidPoints(trimmedPoints)) {
            throw new ParseException(Points.MESSAGE_CONSTRAINTS);
        }
        return new Points(trimmedPoints);
    }

    /**
     * Parses a {@code String order} into an {@code Order}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code order} is invalid.
     */
    public static Order parseOrder(String order) throws ParseException {
        requireNonNull(order);
        String trimmedOrder = order.trim();
        if (!Address.isValidAddress(trimmedOrder)) {
            throw new ParseException(Order.MESSAGE_CONSTRAINTS);
        }
        return new Order(trimmedOrder);
    }


}
