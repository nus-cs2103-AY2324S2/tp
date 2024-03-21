package seedu.address.model.schedule;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

import seedu.address.model.person.Person;

/**
 * Represents a Schedule in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSchedName(String)},
 * timings are valid as declared in {@link #isValidTiming(LocalDateTime, LocalDateTime)}
 */
public class Schedule {

    public static final String MESSAGE_CONSTRAINTS = "Schedule names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";
    public static final DateTimeFormatter CUSTOM_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static int schedIdCounter = 0;
    // !! to EDIT as a metadata for Json storage

    private final int schedId;
    private final String schedName;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final ArrayList<Person> personList;

    /**
     * Constructs a {@code Schedule}.
     *
     * @param schedName A valid schedule name
     * @param startTime A valid start time
     * @param endTime A valid end time
     */

    public Schedule(String schedName, LocalDateTime startTime,
                    LocalDateTime endTime) {
        requireNonNull(schedName);
        //checkArgument(isValidSchedName(schedName), MESSAGE_CONSTRAINTS);
        checkArgument(isValidTiming(startTime, endTime));
        this.schedId = schedIdCounter++;
        this.schedName = schedName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.personList = new ArrayList<>();
    }

    public String getSchedName() {
        return schedName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getParticipants() {
        StringBuilder participants = new StringBuilder();
        for (Person person: personList) {
            participants.append(person.getName());
        }
        return participants.toString();
    }

    /**
     * Add new person(s) into personList if they are not added yet
     *
     * @param newParticipants
     */
    public ArrayList<Person> addParticipants(ArrayList<Person> newParticipants) {
        ArrayList<Person> addedParticipants = new ArrayList<>();
        for (Person p: newParticipants) {
            personList.add(p);
            /*for (Person existingP: personList) {
                if (p.isSamePerson(existingP)) {
                    continue;
                }
                personList.add(p);
                addedParticipants.add(p);
            }*/
        }
        return addedParticipants;
    }

    /**
     * Returns true if a given string is a valid schedule name.
     */
    public static boolean isValidSchedName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if startTime before endTime
     */
    public static boolean isValidTiming(LocalDateTime startTime, LocalDateTime endTime) {
        return startTime.isBefore(endTime);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameSchedule(Schedule otherSchedule) {
        if (otherSchedule == this) {
            return true;
        }

        return otherSchedule != null
                && otherSchedule.getSchedName().equals(getSchedName());
    }

    /**
     * Returns true if both schedules have the same identity and data fields.
     * This defines a stronger notion of equality between two schedules.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Schedule)) {
            return false;
        }

        Schedule otherSched = (Schedule) other;
        return schedName.equals(otherSched.schedName)
                && startTime.equals(otherSched.startTime)
                && endTime.equals(otherSched.endTime)
                && personList.equals(otherSched.personList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedName, startTime, endTime, personList);
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return schedName
                + " start " + startTime.toString()
                + " end " + endTime.toString()
                + " participants " + getParticipants();
    }

}
