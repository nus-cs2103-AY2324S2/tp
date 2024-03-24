package seedu.address.logic.parser.fuzzy;

import java.util.Arrays;

/**
 * Parsers user commands with fuzzy matching
 */
public class FuzzyCommandParser {
    private static final BkTreeCommandMatcher<String> commandTree = new BkTreeCommandMatcher<>(Arrays.asList(
            "add", "edit", "delete", "remove", "clear", "find", "list", "exit", "help", "sort", "addbystep", "filter"));
    private static final int MAX_DISTANCE = 1;

    /**
     * Parses user input with fuzzy matching
     *
     * @param userInput the user input to be parsed
     * @return closest matched command, "error" if no match is found within MAX_DISTANCE
     */
    public static String parseCommand(String userInput) {
        String userInputLowerCase = userInput.toLowerCase();
        String closestMatch = commandTree.findClosestMatch(userInputLowerCase);
        if (commandTree.getClosestDistance() > MAX_DISTANCE) {
            return "error";
        }
        return closestMatch;
    }
}
