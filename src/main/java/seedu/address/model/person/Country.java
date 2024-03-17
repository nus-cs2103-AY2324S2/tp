package seedu.address.model.person;

public class Country {
    private final String COUNTRY;
    //todo: map input country to a standard country name
    public Country(String country) {
        this.COUNTRY = country;
    }
    public String getCountry() {
        return COUNTRY;
    }
    @Override
    public String toString() {
        return this.COUNTRY;
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
        return COUNTRY.equals(otherCountry.COUNTRY);
    }
}
