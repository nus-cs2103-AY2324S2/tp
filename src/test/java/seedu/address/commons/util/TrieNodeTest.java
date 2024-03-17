package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class TrieNodeTest {

    @Test
    void getChild() {
        TrieNode trieNode = new TrieNode('a');
        assertNull(trieNode.getChild('b'));

        trieNode.setChild('b');
        assertNotNull(trieNode.getChild('b'));

        trieNode.setChild('c');
        assertNotNull(trieNode.getChild('c'));

        trieNode.deleteChild('b');
        assertNull(trieNode.getChild('b'));
    }

    @Test
    void getChildren() {
        TrieNode trieNode = new TrieNode('a');
        assertNotNull(trieNode.getChildren());

        assert(trieNode.getChildren().isEmpty());

        trieNode.setChild('b');
        trieNode.setChild('c');
        assertEquals(2, trieNode.getChildren().size());

        trieNode.deleteChild('b');
        assertEquals(1, trieNode.getChildren().size());
    }
    @Test
    void testEquals() {
        TrieNode trieNode = new TrieNode('a');
        TrieNode trieNode2 = new TrieNode('a');

        assertEquals(trieNode, trieNode2);

        trieNode.setEndOfWord(true);
        assertNotEquals(trieNode, trieNode2);
    }
}
