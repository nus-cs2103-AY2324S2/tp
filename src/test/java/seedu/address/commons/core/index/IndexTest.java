package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createOneBasedIndex() {

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    public void createZeroBasedIndex() {

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    public void equals() {
        final Index fifthCourseMateIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertTrue(fifthCourseMateIndex.equals(Index.fromOneBased(5)));
        assertTrue(fifthCourseMateIndex.equals(Index.fromZeroBased(4)));

        // same object -> returns true
        assertTrue(fifthCourseMateIndex.equals(fifthCourseMateIndex));

        // null -> returns false
        assertFalse(fifthCourseMateIndex.equals(null));

        // different types -> returns false
        assertFalse(fifthCourseMateIndex.equals(5.0f));

        // different index -> returns false
        assertFalse(fifthCourseMateIndex.equals(Index.fromOneBased(1)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromZeroBased(0);
        String expected = Index.class.getCanonicalName() + "{zeroBasedIndex=" + index.getZeroBased() + "}";
        assertEquals(expected, index.toString());
    }
}
