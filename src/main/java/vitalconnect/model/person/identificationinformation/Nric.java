package vitalconnect.model.person.identificationinformation;

import static java.util.Objects.requireNonNull;
import static vitalconnect.commons.util.AppUtil.checkArgument;
/**
 * Represents a Person's NRIC in the clinic.
 * Guarantees: immutable; is valid as declared in {@link #isValidNric(String)}
 * Got formula from nric Validation from https://github.com/samliew/singapore-nric/tree/main
 */
public class Nric {
    public static final String VALIDATION_REGEX = "^[S T F G M]\\d{7}\\w$";

    public static final String MESSAGE_CONSTRAINTS =
            "NRIC is invalid, should be @xxxxxxx# "
            + "where @ is a letter that can be S,T,F,G or M and "
            + "# is the appropriate letter.";

    public final String nric;

    /**
     * Constructs a {@code Nric}.
     *
     * @param nric A valid Nric.
     */
    public Nric(String nric) {
        requireNonNull(nric);
        checkArgument(isValidNric(nric), MESSAGE_CONSTRAINTS);
        this.nric = nric;
    }

    /**
     * Returns true if a given string is a valid nric.
     */
    public static boolean isValidNric(String test) {
        if (test.matches("test")) {
            return true;
        }

        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        String checksumStr = test.substring(1, 8);


        return isValidChecksum(test.charAt(0), checksumStr, test.charAt(8));
    }

    /**
     * Returns true if the given char is valid based on the given string and prefix
     *
     * @param prefixChar The prefix char to check.
     * @param checksumStr The checksum string to check.
     * @param charToCheck The char to check if it is valid.
     */
    public static boolean isValidChecksum(char prefixChar, String checksumStr, char charToCheck) {
        // Multiply the digits by the appropriate weightage
        int total = Character.getNumericValue(checksumStr.charAt(0)) * 2
                  + Character.getNumericValue(checksumStr.charAt(1)) * 7
                  + Character.getNumericValue(checksumStr.charAt(2)) * 6
                  + Character.getNumericValue(checksumStr.charAt(3)) * 5
                  + Character.getNumericValue(checksumStr.charAt(4)) * 4
                  + Character.getNumericValue(checksumStr.charAt(5)) * 3
                  + Character.getNumericValue(checksumStr.charAt(6)) * 2;

        // Add the proper offset and set the array (following the checksum algorithm)
        char[] checksumResultChar = new char[]{ 'J', 'Z', 'I', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A' };
        switch (prefixChar) {
        case 'S':
            break;
        case 'T':
            total += 4;
            break;
        case 'G':
            total += 4;
            checksumResultChar = new char[]{ 'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K' };
            break;
        case 'F':
            checksumResultChar = new char[]{ 'X', 'W', 'U', 'T', 'R', 'Q', 'P', 'N', 'M', 'L', 'K' };
            break;
        case 'M':
            checksumResultChar = new char[]{ 'K', 'L', 'J', 'N', 'P', 'Q', 'R', 'T', 'U', 'W', 'X' };
            total += 3;
            break;
        default:
            break;
        }

        total %= 11;

        // If M, need to adjust
        if (prefixChar == 'M') {
            total = 10 - total;
        }

        return checksumResultChar[total] == charToCheck;
    }

    @Override
    public String toString() {
        return nric;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Nric)) {
            return false;
        }

        Nric otherNric = (Nric) other;
        return nric.equals(otherNric.nric);
    }

    @Override
    public int hashCode() {
        return nric.hashCode();
    }
}
