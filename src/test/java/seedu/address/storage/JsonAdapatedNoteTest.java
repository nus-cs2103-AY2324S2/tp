package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

public class JsonAdapatedNoteTest {

    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2024, 1, 1, 12, 30);
    private static final String VALID_DESCRIPTION = "General Flu";
    // TODO: add valid person index for testing
//    private static final Index VALID_PERSON_INDEX = ParserUtil.parseIndex(" 1");
    private static final String INVALID_DESCRIPTION = "";

    @Nested
    class ConstructorTests {
        @Test
        public void constructor_jsonProperty_returnsNote() {
            JsonAdapatedNote jsonAdapatedNote = new JsonAdapatedNote(TEST_DATE_TIME, VALID_DESCRIPTION);

            assertNotNull(jsonAdapatedNote);
        }

        @Test
        public void constructor_note_returnsNote() {
            JsonAdapatedNote jsonAdapatedNote =
                new JsonAdapatedNote(new Note(TEST_DATE_TIME, new Description(VALID_DESCRIPTION)));

            assertNotNull(jsonAdapatedNote);
        }
    }

    @Nested
    class ToModelTypeTests {

        @Test
        public void toModelType_success_returnsNote() throws Exception {
            Note original = new Note(TEST_DATE_TIME, new Description(VALID_DESCRIPTION));
            JsonAdapatedNote jsonAdapatedNote = new JsonAdapatedNote(original);
            Note modelNote = jsonAdapatedNote.toModelType();

            assertEquals(original, modelNote);
        }

        @Test
        public void toModelType_invalidDescription_throwsIllegalValueException() {
            JsonAdapatedNote jsonAdapatedNote = new JsonAdapatedNote(TEST_DATE_TIME, INVALID_DESCRIPTION);

            assertThrows(IllegalValueException.class, jsonAdapatedNote::toModelType);
        }

        @Test
        public void toModelType_nullDescription_throwsIllegalValueException() {
            JsonAdapatedNote jsonAdapatedNote = new JsonAdapatedNote(TEST_DATE_TIME, null);

            assertThrows(IllegalValueException.class, jsonAdapatedNote::toModelType);
        }
    }

}
