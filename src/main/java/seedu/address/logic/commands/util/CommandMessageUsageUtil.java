package seedu.address.logic.commands.util;

/**
 * A container for {@code Command} specific utility functions.
 * <p>
 *
 */
public class CommandMessageUsageUtil {
    /**
     * Generates the message usage, given the {@code commandWord}, {@code description},
     * and {@code example} as strings.
     */
    public static String generateMessageUsage(String commandWord, String description,
                                              String example) {
        return commandWord + ": "
                + description + "\n"
                + "Example: " + example;
    }

    /**
     * Generates the message usage, given the {@code commandWord}, {@code description},
     * {@code parameters}, and {@code example} as strings.
     */
    public static String generateMessageUsage(String commandWord, String description,
                                              String parameters, String example) {
        return commandWord + ": "
                + description + "\n"
                + "Parameters: " + parameters + "\n"
                + "Example: " + example;
    }

    /**
     * Generates the message usage, given the {@code commandWord}, {@code description} as strings,
     * and the {@code parameters}.
     */
    public static String generateMessageUsage(String commandWord, String description,
                                              Parameter... parameters) {
        return generateMessageUsage(
                commandWord,
                description,
                generateMessageUsageParameters(parameters),
                generateMessageUsageExample(commandWord, parameters)
        );
    }

    /**
     * Generates the message usage parameters, given the {@code parameters}.
     */
    public static String generateMessageUsageParameters(Parameter... parameters) {
        StringBuilder stringBuilder = new StringBuilder();

        for (Parameter p : parameters) {
            stringBuilder
                    .append(p.getFormattedParameterDetails())
                    .append(" ");
        }

        return stringBuilder.toString().trim();
    }

    /**
     * Generates the message usage example, given the {@code commmandWord} and {@code parameters}.
     */
    public static String generateMessageUsageExample(String commandWord, Parameter... parameters) {
        StringBuilder stringBuilder = new StringBuilder(commandWord + " ");

        for (Parameter p : parameters) {
            String exampleValue = p.getParameterWithExampleValues();
            if (!exampleValue.isBlank()) {
                stringBuilder
                        .append(exampleValue)
                        .append(" ");
            }
        }

        return stringBuilder.toString().trim();
    }
}
