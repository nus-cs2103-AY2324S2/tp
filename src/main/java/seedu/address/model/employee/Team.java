package seedu.address.model.employee;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Employee's team in the address book.
 *  * Guarantees: immutable; is valid as declared in {@link #isValidTeam(String)}
 */
public class Team {

    public static final String MESSAGE_CONSTRAINTS =
            "Teams can only be a single string of alphanumeric characters, and it should not be blank";

    /*
     * The team should be a singular string of alphanumeric characters.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]";

    public final String teamName;

    /**
     * Constructs a {@code Team}.
     *
     * @param team A valid team.
     */
    public Team(String team) {
        requireNonNull(team);
        checkArgument(isValidTeam(team), MESSAGE_CONSTRAINTS);
        teamName = team;
    }

    /**
     * Returns true if a given string is a valid team.
     */
    public static boolean isValidTeam(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return "Team " + teamName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) other;
        return teamName.equals(otherTeam.teamName);
    }

    @Override
    public int hashCode() {
        return teamName.hashCode();
    }

}
