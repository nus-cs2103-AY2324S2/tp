package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {

    public static final String DEFAULT_DESCRIPTION = "Description of Meeting";
    public static final String DEFAULT_DATE = "01-01-2030 17:00";
    public static final Person DEFAULT_CLIENT = new PersonBuilder().build();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private String description;
    private LocalDateTime dateTime;
    private Person client;

    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        description = DEFAULT_DESCRIPTION;
        dateTime = LocalDateTime.parse(DEFAULT_DATE, formatter);
        client = DEFAULT_CLIENT;
    }
    /**
     * Creates a {@code MeetingBuilder} with the default details, but with the specified person.
     */
    public MeetingBuilder(Person person) {
        description = DEFAULT_DESCRIPTION;
        dateTime = LocalDateTime.parse(DEFAULT_DATE, formatter);
        client = person;
    }

    /**
     * Constructs a {@code MeetingBuilder} with the specified meeting to copy.
     *
     * @param meetingToCopy The meeting to copy.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        description = meetingToCopy.getDescription();
        dateTime = meetingToCopy.getDateTime();
        client = meetingToCopy.getClient();
    }


    /**
     * Sets the {@code description} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime, formatter);
        return this;
    }

    /**
     * Sets the {@code client} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withClient(Person client) {
        this.client = client;
        return this;
    }

    /**
     * Builds and returns a {@code Meeting} object with the specified details.
     */
    public Person build() {
        Meeting meeting = new Meeting(description, dateTime, client);
        return client;
    }
}
