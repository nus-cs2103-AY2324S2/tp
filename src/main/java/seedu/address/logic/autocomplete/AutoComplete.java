package seedu.address.logic.autocomplete;

/**
 * API of the Autocomplete component
 */
public interface AutoComplete {
    /**
     * Returns the autocomplete text based on the input.
     *
     * @param input The input as entered by the user.
     * @return the autocomplete text to be appended to the input.
     */
    String getAutoComplete(String input);
}
