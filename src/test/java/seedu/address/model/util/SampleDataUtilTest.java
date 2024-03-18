package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.illness.Illness;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;

public class SampleDataUtilTest {
    private static final LocalDateTime TEST_DATE_TIME = LocalDateTime.of(2024, 1, 1, 12, 30);

    @Nested
    class GetSampleAddressBookTests {
        @Test
        public void getSampleAddressBook_success_returnsAddressBook() {
            ReadOnlyAddressBook result = SampleDataUtil.getSampleAddressBook();
            assertNotNull(result);
            assertFalse(result.getPersonList().isEmpty());
        }
    }

    @Nested
    class GetTagsTests {
        @Test
        void getTags_success_returnsEmptySet() {
            Set<Illness> illnesses = SampleDataUtil.getIllnesses();

            assertTrue(illnesses.isEmpty());
        }

        @Test
        public void getTags_success_returnsPopulatedSet() {
            String[] tags = {"friend", "colleague"};
            Set<Illness> result = SampleDataUtil.getIllnesses(tags);

            assertEquals(2, result.size());
            assertTrue(result.contains(new Illness(tags[0])));
            assertTrue(result.contains(new Illness(tags[1])));
        }
    }

    @Nested
    class GetNotesTests {
        @Test
        public void getNotes_success_returnsEmptyList() {
            Note[] notes = {};
            ObservableList<Note> result = SampleDataUtil.getNotes(notes);

            assertTrue(result.isEmpty());
        }

        @Test
        public void getNotes_success_returnsPopulatedList() {
            var note1 = new Note(TEST_DATE_TIME, new Description("Sample note 1"));
            var note2 = new Note(TEST_DATE_TIME, new Description("Sample note 2"));
            Note[] notes = {note1, note2};
            ObservableList<Note> result = SampleDataUtil.getNotes(notes);

            assertEquals(2, result.size());
            assertEquals(note1, result.get(0));
            assertEquals(note2, result.get(1));
        }
    }
}
