package seedu.address.model.person;

import java.util.Objects;

/**
 * Represents a Person's condition in the address book.
 * Guarantees: immutable;
 */
public class Country {
    private final String country;
    //todo: map input country to a standard country name
    public Country(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    @Override
    public String toString() {
        return this.country;
    }
    @Override
    public int hashCode() {
        return country.hashCode();
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Country)) {
            return false;
        }
        Country otherCountry = (Country) other;
        return country.equals(otherCountry.country);
    }
}
