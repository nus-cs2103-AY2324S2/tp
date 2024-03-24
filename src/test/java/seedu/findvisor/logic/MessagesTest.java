package seedu.findvisor.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_MEETING_NO_REMARK;
import static seedu.findvisor.logic.commands.CommandTestUtil.VALID_MEETING_WITH_REMARK;

import org.junit.jupiter.api.Test;

import seedu.findvisor.model.person.Meeting;

public class MessagesTest {
    @Test
    public void formatMeeting_noRemark() {
        Meeting meeting = VALID_MEETING_NO_REMARK;
        String expected = String.format("Meeting Start: %s; End: %s",
                meeting.getStartString(), meeting.getEndString());
        assertEquals(expected, Messages.format(meeting));
    }

    @Test
    public void formatMeeting_withRemark() {
        Meeting meeting = VALID_MEETING_WITH_REMARK;
        String expected = String.format("Meeting Start: %s; End: %s\nMeeting Remark: %s",
                meeting.getStartString(), meeting.getEndString(), meeting.remark);
        assertEquals(expected, Messages.format(meeting));
    }

}
