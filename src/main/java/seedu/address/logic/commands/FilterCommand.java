package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Role;
import seedu.address.model.applicant.Stage;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
/**
 * Adds a person to the address book.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters applications by tags. "
            + "Parameters: "
            + PREFIX_ROLE + " roles " +
            PREFIX_STAGE + " stages ";

    public static final String MESSAGE_SUCCESS = "Persons Filtered: ";
    private final Role filteredRole;
    private final Stage filteredStage;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public FilterCommand(Optional<Role> filteredRole, Optional<Stage> filteredStage) {
        //requireNonNull(filteredRole);
        //requireNonNull(filteredStage);
        if (filteredRole.equals(Optional.empty())) {
            this.filteredRole = new Role("");
        } else {
            this.filteredRole = filteredRole.get();
        }
        if (filteredStage.equals(Optional.empty())) {
            this.filteredStage = new Stage("");
        } else {
            this.filteredStage = filteredStage.get();
        }

    }

    public FilterCommand(Role filteredRole) {
        requireNonNull(filteredRole);
        this.filteredRole = filteredRole;
        this.filteredStage = new Stage("");
    }

    public FilterCommand(Stage filteredStage) {
        requireNonNull(filteredStage);
        this.filteredRole = new Role("");
        this.filteredStage = filteredStage;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Predicate<Person> matchesCriteria = person -> {
            if (!(person instanceof Applicant)) {
                return false;
            }
            Applicant applicant = (Applicant) person;

            // Check if the roleName matches filteredRole and stageName matches filteredStage
            boolean roleMatches = applicant.getRole().equals(filteredRole);
            boolean stageMatches = applicant.getStage().equals(filteredStage);
            if (filteredRole.roleName.isEmpty()) {
                roleMatches = true;
            } else if (filteredStage.stageName.isEmpty()) {
                stageMatches = true;
            }

            return  roleMatches && stageMatches;
        };
        model.updateFilteredPersonList(matchesCriteria);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherAddCommand = (FilterCommand) other;
        return filteredRole.equals(otherAddCommand.filteredRole) && filteredStage.equals(otherAddCommand.filteredStage);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toFilterRole", this.filteredRole)
                .add("toFilterStage", this.filteredStage)
                .toString();
    }
}
