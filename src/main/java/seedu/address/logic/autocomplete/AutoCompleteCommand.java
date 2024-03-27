package seedu.address.logic.autocomplete;

import seedu.address.commons.util.Trie;

/**
 * Auto complete commands
 */
public class AutoCompleteCommand implements AutoComplete {
    private static Trie commandTrie;

    /**
     * Initializes the command trie with the given commands. This method should be called once at
     * the start of the initialization of LogicManager.
     *
     * @param commands the commands to initialize the trie with
     * @see seedu.address.logic.LogicManager
     */
    public static void initialize(String... commands) {
        commandTrie = new Trie(commands);
    }

    @Override
    public String getAutoComplete(String input) {
        assert commandTrie != null;
        String fullCommand = commandTrie.findFirstWordWithPrefix(input);

        assert fullCommand != null;
        if (fullCommand.isEmpty()) {
            return "";
        }

        // Strip the input from the full command
        return fullCommand.substring(input.length());
    }
}
