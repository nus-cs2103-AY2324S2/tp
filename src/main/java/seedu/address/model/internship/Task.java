package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.model.internship.Deadline.isValidDeadline;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an internship's Task in the internship book
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS =
            "Task should not be blank!";

    /*
     * Matches any characters that are not only whitespace
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";

    public final String task;

    private Deadline deadline;

    private boolean isDeadlineSet = false;

    /**
     * Constructs a {@code Task}.
     *
     * @param task A valid task.
     */
    public Task(String task) {
        requireNonNull(task);
        checkArgument(isValidTask(task), MESSAGE_CONSTRAINTS);
        this.task = task;
    }

    /**
     * Constructs a {@code Task}.
     * @param task
     * @param deadline
     */
    public Task(String task, String deadline) {
        requireNonNull(task);
        requireNonNull(deadline);
        checkArgument(isValidTask(task), MESSAGE_CONSTRAINTS);
        checkArgument(isValidDeadline(deadline), MESSAGE_CONSTRAINTS);
        this.task = task;
        this.deadline = new Deadline(deadline);
        isDeadlineSet = true;
    }

    /**
     * Constructs a {@code Task}.
     * @param task
     * @param deadline
     * @param isDeadlineSet
     */
    @JsonCreator
    public Task(@JsonProperty("task") String task,
                @JsonProperty("deadline") Map<String, String> deadlineMap,
                @JsonProperty("isDeadlineSet") boolean isDeadlineSet) {
        requireNonNull(task);
        checkArgument(isValidTask(task), MESSAGE_CONSTRAINTS);
        this.task = task;

        if (isDeadlineSet && deadlineMap != null && deadlineMap.containsKey("deadline")) {
            String deadlineStr = deadlineMap.get("deadline");
            this.deadline = new Deadline(deadlineStr);
        } else {
            this.deadline = null;
        }
        this.isDeadlineSet = isDeadlineSet;
    }


    /**
     * Returns true if a given string is a valid task.
     */
    public static boolean isValidTask(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid task.
     */
    public String convertToJsonString() {
        return "{\"task\": \"" + task + "\", \"deadline\": \"" + deadline + "\"}";
    }

    /**
     * Replace deadline with {@code deadline}.
     */
    public void addDeadline(Deadline deadline) {
        this.deadline = deadline;
        isDeadlineSet = true;
    }

    /**
     * Returns the task string.
     */
    @Override
    public String toString() {
        if (isDeadlineSet) {
            return task + " Deadline: " + deadline;
        } else {
            return task;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Task)) {
            return false;
        }

        Task otherName = (Task) other;
        return task.equals(otherName.task) && isDeadlineSet == otherName.isDeadlineSet
                && (isDeadlineSet ? deadline.equals(otherName.deadline) : true);
    }

    @Override
    public int hashCode() {
        return task.hashCode();
    }
}
