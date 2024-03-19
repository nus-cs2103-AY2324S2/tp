package seedu.address.commons.util;

/**
 * A class that represents a Trie data structure.
 */
public class Trie {
    private final TrieNode root;

    /**
     * Constructor for Trie. Accepts a list of words to be inserted into the Trie.
     * @param words the words to be inserted into the Trie
     */
    public Trie(String... words) {
        this.root = new TrieNode(null);
        for (String word : words) {
            insert(word);
        }
    }

    //@@author <a href="https://github.com/eugenp/tutorials">eugenp</a>
    //Reused from https://www.baeldung.com/trie-java with minor modifications
    /**
     * Insert a word into the Trie.
     * @author <a href="https://github.com/eugenp/tutorials">eugenp</a>
     * @param word the word to be inserted
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.getChild(c) == null) {
                current.setChild(c);
            }
            current = current.getChild(c);
        }
        current.setEndOfWord(true);
    }
    //@@author

    //@@author <a href="https://github.com/eugenp/tutorials">eugenp</a>
    //Reused from https://www.baeldung.com/trie-java with minor modifications
    /**
     * Delete a word from the Trie.
     * @author <a href="https://github.com/eugenp/tutorials">eugenp</a>
     * @param word the word to be deleted
     */
    public void delete(String word) {
        delete(root, word, 0);
    }
    //@@author

    //@@author <a href="https://github.com/eugenp/tutorials">eugenp</a>
    //Reused from https://www.baeldung.com/trie-java with minor modifications
    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord()) {
                return false;
            }
            current.setEndOfWord(false);
            return current.getChildren().isEmpty();
        }

        char c = word.charAt(index);
        TrieNode node = current.getChild(c);
        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isEndOfWord();
        if (shouldDeleteCurrentNode) {
            current.deleteChild(c);
            return current.getChildren().isEmpty();
        }

        return false;
    }
    //@@author

    //@@author <a href="https://github.com/eugenp/tutorials">eugenp</a>
    //Reused from https://www.baeldung.com/trie-java with minor modifications
    /**
     * Search for a word in the Trie.
     * @author <a href="https://github.com/eugenp/tutorials">eugenp</a>
     * @param word the word to be searched
     * @return true if the word is found, false otherwise
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.getChild(c) == null) {
                return false;
            }
            current = current.getChild(c);
        }
        return current.isEndOfWord();
    }
    //@@author

    /**
     * Find the first word in the Trie that starts with the given prefix.
     * @param prefix the prefix to be searched
     * @return the first word that starts with the given prefix. If no such word exists, return null.
     */
    public String findFirstWordWithPrefix(String prefix) {
        TrieNode current = root;
        StringBuilder sb = new StringBuilder();
        for (char c : prefix.toCharArray()) {
            if (current.getChild(c) == null) {
                return null;
            }
            sb.append(c);
            current = current.getChild(c);
        }
        return findFirstWordWithPrefixHelper(current, sb);
    }

    private String findFirstWordWithPrefixHelper(TrieNode current, StringBuilder sb) {
        if (current.isEndOfWord()) {
            return sb.toString();
        }
        for (char c : current.getChildren().keySet()) {
            sb.append(c);
            return findFirstWordWithPrefixHelper(current.getChild(c), sb);
        }
        return null;
    }
}
