package scrolls.elder.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.model.log.Log;

/**
 * Wraps data for all logs stored.
 * All logs stored are guaranteed to have IDs consistent with the {@code logIdSequence} of the {@code LogStore}
 * instance.
 */
public class LogStore implements ReadOnlyLogStore {
    private final ObservableMap<Integer, Log> logs;
    private final ObservableList<Log> logList;

    /**
     * The sequence number that determines the ID of the next log to be added.
     */
    private int logIdSequence;

    /**
     * Creates an empty LogStore.
     */
    public LogStore() {
        this.logIdSequence = 0;
        this.logs = FXCollections.observableHashMap();
        this.logList = FXCollections.observableArrayList();

        // Set up links between the ObservableList used for rendering and the backing ObservableMap
        MapChangeListener<? super Integer, ? super Log> listener = change -> {
            if (change.wasRemoved()) {
                logList.remove(change.getValueRemoved());
            }
            if (change.wasAdded()) {
                logList.add(change.getValueAdded());
            }
        };
        logs.addListener(listener);
    }

    /**
     * Creates an LogStore using the Logs in {@code toBeCopied}
     */
    public LogStore(ReadOnlyLogStore toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// Collection-level getters and setters

    @Override
    public ObservableList<Log> getLogList() {
        return FXCollections.unmodifiableObservableList(logList);
    }

    /**
     * Replaces the contents of the log list with {@code logs}.
     */
    public void setLogList(List<Log> logs) {
        this.logs.clear();
        int maxId = -1;
        for (Log log : logs) {
            maxId = Math.max(maxId, log.getLogId());
            this.logs.put(log.getLogId(), log);
        }
        logIdSequence = maxId + 1;
    }

    /**
     * Resets the existing data of this {@code LogStore} with {@code newData}.
     */
    public void resetData(ReadOnlyLogStore newData) {
        requireNonNull(newData);
        setLogList(newData.getLogList());
    }

    //// Log-level CRUD operations

    /**
     * Returns true if a log with the same identity as {@code log} exists in the store.
     */
    public boolean hasLog(Log log) {
        requireNonNull(log);

        return logs.containsKey(log.getLogId());
    }

    /**
     * Adds a log to the store.
     * Any relational validation (i.e., volunteerId) should be done before calling this method.
     */
    public void addLog(Log newLog) {
        requireNonNull(newLog);

        logs.put(logIdSequence, new Log(logIdSequence, newLog));
        logIdSequence++;
    }

    /**
     * Adds an existing log to the store.
     * For the case where existing logs are read from storage.
     */
    public void addLogWithId(Log newLog) {
        requireNonNull(newLog);

        logs.put(newLog.getLogId(), newLog);
        if (newLog.getLogId() >= logIdSequence) {
            logIdSequence = newLog.getLogId() + 1;
        }
    }

    /**
     * Returns the log with the given ID.
     * {@code logId} must exist in the store.
     */
    public Log getLogById(int logId) {
        return logs.get(logId);
    }

    /**
     * Updates a log with the given data from {@code editedLog}.
     * {@code editedLog} must contain an ID that matches an existing log in the store.
     */
    public void setLog(Log editedLog) {
        requireNonNull(editedLog);

        logs.put(editedLog.getLogId(), editedLog);
    }

    /**
     * Removes the log with the given ID from the store.
     * {@code idToRemove} must exist in the store.
     */
    public void removeLog(int idToRemove) {
        logs.remove(idToRemove);
    }

    //// Overrides

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("logs", logList)
            .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LogStore)) {
            return false;
        }

        LogStore otherLogStore = (LogStore) other;
        return logs.equals(otherLogStore.logs);
    }

    @Override
    public int hashCode() {
        return logs.hashCode();
    }
}
