package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class QueryableCourseMateTest {

    @Test
    public void constructor_indexNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new QueryableCourseMate((Index) null));
    }

    @Test
    public void constructor_nameNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new QueryableCourseMate((Name) null));
    }

    @Test
    public void isName_nameObject_returnsTrue() {
        Name name = new Name("John Doe");
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(name);
        assertTrue(queryableCourseMate.isName());
        assertEquals(queryableCourseMate.getName(), name);
    }

    @Test
    public void isIndex_nameObject_returnsFalse() {
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(new Name("John Doe"));
        assertFalse(queryableCourseMate.isIndex());
    }

    @Test
    public void isName_indexObject_returnsFalse() {
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(Index.fromZeroBased(0));
        assertFalse(queryableCourseMate.isName());
    }

    @Test
    public void isIndex_indexObject_returnsTrue() {
        Index index = Index.fromZeroBased(0);
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(index);
        assertTrue(queryableCourseMate.isIndex());
        assertEquals(queryableCourseMate.getIndex(), index);
    }

    @Test
    public void equals() {
        QueryableCourseMate indexZero1 = new QueryableCourseMate(Index.fromZeroBased(0));
        QueryableCourseMate indexZero2 = new QueryableCourseMate(Index.fromZeroBased(0));
        QueryableCourseMate indexOne = new QueryableCourseMate(Index.fromZeroBased(1));

        QueryableCourseMate hello1 = new QueryableCourseMate(new Name("Hello"));
        QueryableCourseMate hello2 = new QueryableCourseMate(new Name("Hello"));
        QueryableCourseMate hi = new QueryableCourseMate(new Name("Hi"));

        assertEquals(indexZero1, indexZero2);
        assertEquals(indexZero2, indexZero1);
        assertNotEquals(indexZero1, indexOne);
        assertNotEquals(indexZero1, hello1);

        assertEquals(hello1, hello2);
        assertEquals(hello2, hello1);
        assertNotEquals(hi, hello1);
        assertNotEquals(hello1, indexOne);
    }
}
