package seedu.address.commons.util;

/**
 * Helper functions to handle Strings while changing them.
 */
public class StringFormatter {

    /**
     * Capitalizes every first letter of the input String. Only capitalizes the first character if it is a letter
     * otherwise it does not attempt to capitalize the first character.
     *
     * @param input Input string.
     * @return The same string, except the first letter of every word is now capitalised.
     */
    public static String capitalizeWords(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder capitalized = new StringBuilder();
        String[] words = input.split("\\s+");

        for (String word : words) {
            if (word.isEmpty()) {
                continue; // Skip empty words
            }

            char firstChar = word.charAt(0);
            capitalized.append(Character.isLetter(firstChar) ? Character.toUpperCase(firstChar) : firstChar);
            capitalized.append(word.substring(1)).append(" ");
        }

        return capitalized.toString().trim();
    }
}
