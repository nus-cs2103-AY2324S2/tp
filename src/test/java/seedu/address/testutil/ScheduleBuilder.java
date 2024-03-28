package seedu.address.testutil;

import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.ScheduleTest;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ScheduleBuilder {
    public static final String DEFAULT_SCHEDNAME = "CS2103 Meeting";
    public static final DateTimeFormatter dateTimeFormatter = Schedule.getScheduleDateTimeFormatter();
    public static final LocalDateTime DEFAULT_START = LocalDateTime.now();
    public static final LocalDateTime DEFAULT_END = LocalDateTime.now().plusHours(1);
    public static final ArrayList<Person> DEFAULT_PERSON_LIST = new ArrayList<>();

    private String schedName;
    private LocalDateTime start;
    private LocalDateTime end;
    private ArrayList<Person> personList;

    /**
     * Creates a {@code ScheduleBuilder} with the default details.
     */
    public ScheduleBuilder() {
        schedName = DEFAULT_SCHEDNAME;
        start = DEFAULT_START;
        end = DEFAULT_END;
        personList = DEFAULT_PERSON_LIST;
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ScheduleBuilder(Schedule scheduleToCopy) {
        schedName = scheduleToCopy.getSchedName();
        start = scheduleToCopy.getStartTime();
        end = scheduleToCopy.getEndTime();
        personList = scheduleToCopy.getPersonList();
    }

    /**
     * Sets the {@code schedName} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withSchedName(String schedName) {
        this.schedName = schedName;
        return this;
    }

    /**
     * Sets the {@code start} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withStartTime(LocalDateTime start) {
        this.start = start;
        return this;
    }

    /**
     * Sets the {@code end} of the {@code Schedule} that we are building.
     */
    public ScheduleBuilder withEndTime(LocalDateTime end) {
        this.end = end;
        return this;
    }

    public ScheduleBuilder withParticipants(ArrayList<Person> personList) {
        this.personList = personList;
        return this;
    }


    public Schedule build() {
        return new Schedule(schedName, start, end, personList);
    }

}
