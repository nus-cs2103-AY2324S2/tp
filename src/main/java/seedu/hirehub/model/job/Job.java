package seedu.hirehub.model.job;

import static java.util.Objects.requireNonNull;
import static seedu.hirehub.commons.util.AppUtil.checkArgument;

import java.util.Objects;

import seedu.hirehub.commons.util.ToStringBuilder;

/**
 * Represents a job opening
 * Guarantees: immutable
 */
public class Job {
    public static final String TITLE_CONSTRAINTS =
            "Titles should contain at least one non whitespace character"
            + "and have a character limit of 100";

    public static final String VACANCY_CONSTRAINTS = "Vacancy must be a positive unsigned integer";
    public static final String VALIDATION_REGEX = "^(?!\\s+$).{1,100}$";

    public final String title;
    public final String description;
    public final int vacancy;

    /**
     * Constructs a {@code Job}
     * @param title Title for the job, which is assumed to be unique across all jobs
     * @param description Details about the job, such as skills required
     * @param vacancy Number of openings for the job
     */
    public Job(String title, String description, int vacancy) {
        requireNonNull(title);
        requireNonNull(description);
        checkArgument(isValidTitle(title), TITLE_CONSTRAINTS);
        this.title = title;
        this.description = description;
        this.vacancy = vacancy;
    }

    public static boolean isValidTitle(String title) {
        return title.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if both jobs have the same name.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
            && title.equals(otherJob.title);
    }

    /**
     * Returns true if both jobs have exactly the same fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }
        Job otherJob = (Job) other;
        return title.equals(otherJob.title)
            && description.equals(otherJob.description)
            && vacancy == otherJob.vacancy;
    }

    /**
     * Gets the job title
     * @return the job title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the job description
     * @return the job description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the job vacancies
     * @return the job vacancies
     */
    public int getVacancy() {
        return vacancy;
    }

    // hash only the title, as different jobs are not allowed to have the same title
    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("title", title)
                .add("description", description)
                .add("vacancy", vacancy)
                .toString();
    }
}
