package scrolls.elder.model.log;

import java.util.Date;
import java.util.Objects;

import javafx.collections.ObservableList;
import scrolls.elder.commons.util.AppUtil;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.person.Person;

/**
 * Represents a log of a volunteer's interaction with a befriendee.
 * Guarantees: immutable; IDs of both Persons are valid.
 */
public class Log {
    public static final String MESSAGE_INVALID_ID = "The volunteer ID or befriendee ID is invalid.";
    private final int volunteerId;
    private final int befriendeeId;
    private final int duration;
    private final Date startDate;
    private final String remarks;

    /**
     * Creates a log with the given volunteer ID and befriendee ID.
     */
    public Log(ReadOnlyDatastore datastore, int volunteerId, int befriendeeId, int duration, Date startDate,
               String remarks) {
        AppUtil.checkArgument(areValidIds(datastore, volunteerId, befriendeeId), MESSAGE_INVALID_ID);
        this.volunteerId = volunteerId;
        this.befriendeeId = befriendeeId;
        this.duration = duration;
        this.startDate = startDate;
        this.remarks = remarks;
    }

    /**
     * Checks if the given IDs are valid for the given datastore.
     */
    public boolean areValidIds(ReadOnlyDatastore datastore, int vid, int bid) {
        ObservableList<Person> persons = datastore.getPersonList();
        boolean volunteerIdExists = false;
        boolean befriendeeIdExists = false;

        for (Person person : persons) {
            if (person.isVolunteer() && person.getId() == vid) {
                volunteerIdExists = true;
            }
            if (!person.isVolunteer() && person.getId() == bid) {
                befriendeeIdExists = true;
            }
        }

        return volunteerIdExists && befriendeeIdExists;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public int getBefriendeeId() {
        return befriendeeId;
    }

    public int getDuration() {
        return duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getRemarks() {
        return remarks;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Log)) {
            return false;
        }

        Log otherLog = (Log) other;
        return volunteerId == otherLog.volunteerId
                && befriendeeId == otherLog.befriendeeId
                && duration == otherLog.duration
                && startDate.equals(otherLog.startDate)
                && remarks.equals(otherLog.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volunteerId, befriendeeId, duration, startDate, remarks);
    }

    @Override
    public String toString() {
        return String.format("Log{Volunteer ID=%d, Befriendee ID=%d, Duration=%d, Start Date=%s, Remarks=%s}",
                volunteerId, befriendeeId, duration, startDate.toString(), remarks);
    }
}
