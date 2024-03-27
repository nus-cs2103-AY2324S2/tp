package seedu.hirehub.model.application;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import seedu.hirehub.commons.util.ToStringBuilder;
import seedu.hirehub.model.job.Job;
import seedu.hirehub.model.person.Person;
import seedu.hirehub.model.status.Status;

/**
 * Represents an association between job and person
 * Guarantees: immutable
 */
public class Application {
    public final Job job;
    public final Status status;
    public final Person person;

    /**
     * Constructs a {@code Job}
     * @param job Job that candidate applied for
     * @param person Candidate who applied for a job
     * @param status Candidate's current application status
     */
    public Application(Person person, Job job, Status status) {
        requireNonNull(person);
        requireNonNull(job);
        requireNonNull(status);
        this.person = person;
        this.job = job;
        this.status = status;
    }

    /**
     * Returns true if both applications contain same job and candidate.
     * This defines a weaker notion of equality between two applications.
     */
    public boolean isSameApplication(Application otherApplication) {
        if (otherApplication == this) {
            return true;
        }

        return otherApplication != null
                && person.isSamePerson(otherApplication.person)
                && job.isSameJob(otherApplication.job);
    }

    /**
     * Returns job that the candidate applied for.
     */
    public Job getJob() {
        return this.job;
    }

    /**
     * Returns person who applied for that job for.
     */
    public Person getPerson() {
        return this.person;
    }

    /**
     * Returns true if both applications have exactly the same fields.
     * This defines a stronger notion of equality between two applications.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Application)) {
            return false;
        }

        Application otherApplication = (Application) other;
        return person.equals(otherApplication.person)
                && job.equals(otherApplication.job)
                && status.equals(otherApplication.status);
    }

    // hash job and person, as different applications are not allowed to have the same job and person
    @Override
    public int hashCode() {
        return Objects.hash(person, job);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Person", person)
                .add("Job", job)
                .add("Status", status)
                .toString();
    }
}
