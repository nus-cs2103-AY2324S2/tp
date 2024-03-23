package seedu.address.model.meeting;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents a Meeting in the address book.
 * Guarantees: details are present and not null, field values are validated.
 * Description is validated to be alphanumeric.
 * Date and time is validated to be in the future and on the hour.
 */
public class Meeting {

    public static final String MESSAGE_CONSTRAINTS = "Description must not be empty, Date must be in dd-MM-yyyy HH:mm";
    public static final String MESSAGE_INVALID_DATE_TIME = "Date must be in dd-MM-yyyy HH:mm format.\n"
            + "Date & time must be after the current time.\n"
            + "Time of appointment must be on the hour.";
    /*
     * description must be alphanumeric
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}][\\p{Alnum} ]*$";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mma");

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


    private final String description;
    private final LocalDateTime dateTime;
    private final Person client;

    /**
     * Constructor for Meeting instance
     * @param description Description of the meeting
     * @param dateTime Time and Date of the meeting
     * @param client Client of the meeting
     */
    public Meeting(String description, LocalDateTime dateTime, Person client) {
        requireAllNonNull(description, dateTime);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateTime(dateTime.format(formatter)), MESSAGE_INVALID_DATE_TIME);
        this.description = description;
        this.dateTime = dateTime;
        this.client = client.addMeeting(this);
    }
    /**
     * Constructor for Meeting instance
     * @param description Description of the meeting
     * @param dateTime Time and Date of the meeting
     */
    public Meeting(String description, LocalDateTime dateTime) {
        requireAllNonNull(description, dateTime);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDateTime(dateTime.format(formatter)), MESSAGE_INVALID_DATE_TIME);
        this.description = description;
        this.dateTime = dateTime;
        this.client = null;
    }

    /**
     * Returns true if a given string is a valid descrption
     * @param test String to be tested
     * @return True if the string is a valid description
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given LocalDateTime is a valid date and time (must be in the future) for an appointment.
     */
    public static boolean isValidDateTime(String test) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            // Attempt to parse the date-time to check format. The result is not used if parsing is successful.
            LocalDateTime parsedDateTime = LocalDateTime.parse(test, formatter);
            // Check if the parsed date-time is before the current system date-time.
            if (parsedDateTime.isBefore(LocalDateTime.now())) {
                return false; // The date-time is in the past.
            }
            return true; // The string is in the correct format.
        } catch (DateTimeParseException e) {
            return false; // The string is not in the correct format.
        }
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("description", description)
                .add("dateTime", dateTime)
                .add("client", getClientName())
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Meeting)) {
            return false;
        }

        Meeting otherMeeting = (Meeting) other;

        return Objects.equals(description, otherMeeting.description)
                && Objects.equals(dateTime, otherMeeting.dateTime);
    }

    @Override
    public int hashCode() {
        // Generate a hashCode based on description and dateTime
        return Objects.hash(description, dateTime);
    }

    /**
     * Returns the client of the meeting.
     *
     * @return Client of the meeting.
     */
    public Person getClient() {
        return this.client;
    }

    public Name getClientName() {
        return this.client.getName();
    }

    /**
     * Returns true if both meetings have the same details.
     */
    public boolean isSameMeeting(Meeting otherMeeting) {
        if (otherMeeting == this) {
            return true;
        }

        return otherMeeting != null
                && otherMeeting.getClient().equals(getClient())
                && otherMeeting.getDescription().equals(getDescription())
                && otherMeeting.getDateTime().equals(getDateTime());
    }

    /**
     * Getter method for the client of the meeting.
     *
     * @return Client of the meeting.
     */
    public Person getPerson() {
        return this.client;
    }

    public boolean belongsTo(Person client) {
        return this.client.isSamePerson(client);
    }

}
