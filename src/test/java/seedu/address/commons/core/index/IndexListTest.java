package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IndexListTest {

    @Test
    public void createIndexList() {
        // creates an IndexList
        assertTrue(IndexList.emptyList() instanceof IndexList);
    }

    @Test
    public void addIndexList() {
        final IndexList indexList = IndexList.emptyList();

        // checks initial list is empty
        assertEquals(0, indexList.size());

        // checks list has 2 elements
        indexList.add(Index.fromOneBased(10));
        indexList.add(Index.fromZeroBased(10));
        assertEquals(2, indexList.size());

        // checks list has 3 elements
        indexList.add(Index.fromOneBased(5));
        indexList.add(Index.fromZeroBased(4));
        assertEquals(3, indexList.size());

        // checks list still has 3 elements
        indexList.add(Index.fromOneBased(11));
        indexList.add(Index.fromOneBased(5));
        assertEquals(3, indexList.size());
    }

    @Test
    public void equals() {
        // null -> return false
        assertFalse(IndexList.emptyList().equals(null));

        // same index order and values -> return true
        assertTrue(createList(1, 2, 3, 4).equals(createList(1, 2, 3, 4)));
        assertTrue(createList(1, 2, 3, 4).equals(createList(1, 1, 2, 1, 3, 4, 2)));
        assertTrue(createList(2, 4, 5, 1).equals(createList(2, 4, 5, 1)));
        assertTrue(createList(1).equals(createList(1, 1, 1, 1, 1, 1, 1, 1)));
        assertTrue(createList(1563).equals(createList(1563)));
        assertTrue(createList().equals(createList()));

        // different order -> return false
        assertFalse(createList(1, 2, 3, 4).equals(createList(4, 3, 2, 1)));
        assertFalse(createList(1, 2, 3, 4).equals(createList(1, 2, 4, 3)));
        assertFalse(createList(1, 2, 4, 3).equals(createList(1, 2, 3, 4)));

        // different size -> return false
        assertFalse(createList(1, 2, 3).equals(createList(1, 2, 2)));
        assertFalse(createList(1, 2).equals(createList(1, 2, 3)));
        assertFalse(createList(1, 2, 3, 4).equals(createList()));

        // different values -> return false
        assertFalse(createList(1, 2, 3, 4).equals(createList(1, 2, 3, 5)));
        assertFalse(createList(1, 2, 3, 4).equals(createList(7, 7, 7, 7)));
        assertFalse(createList(1, 2, 3, 4).equals(createList(2, 3, 4, 5)));
    }

    private IndexList createList(int... numbers) {
        IndexList indexList = IndexList.emptyList();
        for (int num: numbers) {
            indexList.add(Index.fromOneBased(num));
        }
        return indexList;
    }
}
