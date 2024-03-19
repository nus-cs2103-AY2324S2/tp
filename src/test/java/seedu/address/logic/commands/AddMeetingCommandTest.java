package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import java.time.LocalDateTime;

public class AddMeetingCommandTest {

    @Test
    public void constructor_validMeeting_success() {
        AddMeetingCommand meetingCommand = new AddMeetingCommand(LocalDateTime.of(2024, 1, 1, 12, 0), "Sell Insurance", INDEX_FIRST_PERSON);
        assert(meetingCommand!= null);
    }
    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(LocalDateTime.of(2024, 1, 1, 12, 0), null, null));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, "Sell Insurance", null));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, INDEX_FIRST_PERSON));
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null, null, null));
    }

    @Test



}
