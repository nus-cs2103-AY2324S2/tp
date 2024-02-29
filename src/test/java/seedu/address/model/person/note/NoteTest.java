package seedu.address.model.person.note;

import static seedu.address.testutil.Assert.assertThrows;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NoteTest {
    @Nested
    class ConstructorTests {
        @Test
        public void constructor_null_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () -> new Note(null, null));
        }
    }
}
