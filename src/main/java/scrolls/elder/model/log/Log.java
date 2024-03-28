package scrolls.elder.model.log;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javafx.collections.ObservableList;
import scrolls.elder.commons.util.AppUtil;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.ReadOnlyDatastore;
import scrolls.elder.model.person.Person;

/**
 * Represents a log of a volunteer's interaction with a befriendee.
 * Guarantees: immutable; IDs of both Persons are valid.
 */
public class Log {
    public static final String MESSAGE_INVALID_ID = "The volunteer ID or befriendee ID is invalid.";
    public static final int PLACEHOLDER_ID = -1;
    private final int logId;
    private final int volunteerId;
    private final int befriendeeId;
    private final int duration;
    private final Date startDate;
    private final String remarks;

    /**
     * Creates a log with all given fields.
     */
    @JsonCreator
    private Log(@JsonProperty("logId") int logId, @JsonProperty("volunteerId") int volunteerId,
               @JsonProperty("befriendeeId") int befriendeeId, @JsonProperty("duration") int duration,
               @JsonProperty("startDate") Date startDate, @JsonProperty("remarks") String remarks) {
        this.logId = logId;
        this.volunteerId = volunteerId;
        this.befriendeeId = befriendeeId;
        this.duration = duration;
        this.startDate = startDate;
        this.remarks = remarks;
    }

    /**
     * Creates a log with the given volunteer ID and befriendee ID.
     */
    public Log(ReadOnlyDatastore datastore, int volunteerId, int befriendeeId, int duration, Date startDate,
               String remarks) {
        AppUtil.checkArgument(areValidIds(datastore, volunteerId, befriendeeId), MESSAGE_INVALID_ID);
        this.logId = PLACEHOLDER_ID;
        this.volunteerId = volunteerId;
        this.befriendeeId = befriendeeId;
        this.duration = duration;
        this.startDate = startDate;
        this.remarks = remarks;
    }

    /**
     * Creates a log with the given log ID from the data of another Log.
     */
    public Log(int logId, Log log) {
        this.logId = logId;
        this.volunteerId = log.volunteerId;
        this.befriendeeId = log.befriendeeId;
        this.duration = log.duration;
        this.startDate = log.startDate;
        this.remarks = log.remarks;
    }

    /**
     * Checks if the given IDs are valid for the given datastore.
     */
    public boolean areValidIds(ReadOnlyDatastore datastore, int vid, int bid) {
        ObservableList<Person> persons = datastore.getPersonStore().getPersonList();
        boolean volunteerIdExists = false;
        boolean befriendeeIdExists = false;

        for (Person person : persons) {
            if (person.isVolunteer() && person.getPersonId() == vid) {
                volunteerIdExists = true;
            }
            if (!person.isVolunteer() && person.getPersonId() == bid) {
                befriendeeIdExists = true;
            }
        }

        return volunteerIdExists && befriendeeIdExists;
    }

    public int getLogId() {
        return logId;
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
        return new ToStringBuilder(this)
            .add("Log ID", logId)
            .add("Volunteer ID", volunteerId)
            .add("Befriendee ID", befriendeeId)
            .add("Duration", duration)
            .add("Start Date", startDate)
            .add("Remarks", remarks)
            .toString();
    }
}
