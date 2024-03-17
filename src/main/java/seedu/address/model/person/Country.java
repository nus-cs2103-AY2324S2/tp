package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Locale;

/**
 * Represents a Person's country in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidCountry(String)}
 */
public class Country {

    public static final String MESSAGE_CONSTRAINTS = "Country provided must be a valid ISO-3166-1 alpha-2 code,"
            + " which can be found from https://www.iso.org/obp/ui/#search/code/";

    public final String value;

    /**
     * Constructs an {@code Country}.
     *
     * @param country A valid country code.
     */
    public Country(String country) {
        country = country.toUpperCase(); // ensure that input is case-insensitive
        requireNonNull(country);
        checkArgument(isValidCountry(country), MESSAGE_CONSTRAINTS);
        value = country;
    }

    /**
     * Returns true if a given string is a valid country code.
     */
    public static boolean isValidCountry(String test) {
        for (String countryCode: Locale.getISOCountries()) {
            if (test.equals(countryCode)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns country name associated with the country code
     */
    public String getDisplayCountry() {
        return new Locale("", value).getDisplayCountry();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Country)) {
            return false;
        }

        Country otherCountry = (Country) other;
        return value.equals(otherCountry.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
