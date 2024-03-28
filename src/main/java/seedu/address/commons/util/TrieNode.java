package seedu.address.commons.util;

import java.util.HashMap;

/**
 * A class that represents a TrieNode data structure.
 */
public class TrieNode {
    private final HashMap<Character, TrieNode> children;
    private final Character character;
    private boolean isEndOfWord;

    /**
     * Constructor for TrieNode.
     */
    public TrieNode(Character c) {
        this.children = new HashMap<>();
        this.character = c;
        this.isEndOfWord = false;
    }

    /**
     * Retrieve the child node of the given character.
     * @param c the character of the child TrieNode to be retrieved
     * @return the child TrieNode of the given character
     */
    public TrieNode getChild(char c) {
        return children.get(c);
    }

    /**
     * Retrieve all children
     * @return the children of the current TrieNode
     */
    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    /**
     * Set a TrieNode as a child of the current TrieNode.
     * @param c the character of the child TrieNode
     */
    public void setChild(char c) {
        this.children.put(c, new TrieNode(c));
    }

    /**
     * Delete a child TrieNode.
     * @param c the character of the child TrieNode
     */
    public void deleteChild(char c) {
        this.children.remove(c);
    }

    /**
     * Set end of word.
     */
    public void setEndOfWord(boolean isEndOfWord) {
        this.isEndOfWord = isEndOfWord;
    }

    /**
     * Retrieve the end of word status.
     */
    public boolean isEndOfWord() {
        return isEndOfWord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TrieNode)) {
            return false;
        }

        TrieNode otherTrieNode = (TrieNode) other;
        return otherTrieNode.character.equals(this.character)
            && otherTrieNode.isEndOfWord == this.isEndOfWord;
    }

    @Override
    public int hashCode() {
        return character.hashCode();
    }

    @Override
    public String toString() {
        return String.format("TrieNode: %s%s",
            this.character,
            this.isEndOfWord ? "\0" : ""
        );
    }
}
