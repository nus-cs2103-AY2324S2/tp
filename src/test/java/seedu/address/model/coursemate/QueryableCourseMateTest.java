package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertFalse;
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
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(new Name("John Doe"));
        assertTrue(queryableCourseMate.isName());
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
    public void isIndex_indexObject_returnsFalse() {
        QueryableCourseMate queryableCourseMate = new QueryableCourseMate(Index.fromZeroBased(0));
        assertTrue(queryableCourseMate.isIndex());
    }
}
