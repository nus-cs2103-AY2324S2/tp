package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.skill.Skill;

/**
 * Adds a courseMate to the contact list.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a courseMate to the contact list. "
            + "Parameters: NAME"
            + PREFIX_PHONE + " PHONE_NUMBER "
            + PREFIX_EMAIL + " EMAIL "
            + "[" + PREFIX_SKILL + " SKILL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + " John Doe "
            + PREFIX_PHONE + " 98765432 "
            + PREFIX_EMAIL + " johnd@example.com "
            + PREFIX_SKILL + " Python "
            + PREFIX_SKILL + " Java";

    public static final String MESSAGE_SUCCESS = "New courseMate added";
    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "This courseMate already exists in the contact list";

    private final CourseMate toAdd;

    /**
     * Creates an AddCommand to add the specified {@code CourseMate}
     */
    public AddCommand(CourseMate courseMate) {
        requireNonNull(courseMate);
        toAdd = courseMate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasCourseMate(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_MATE);
        }
        Set<Skill> newSkills = getNewSkills(model);

        model.addCourseMate(toAdd);
        model.setRecentlyProcessedCourseMate(toAdd);
        return new CommandResult(messageNewSkill(newSkills) + MESSAGE_SUCCESS,
                false, false, true);
    }

    /**
     * Creates a warning message for newly added skills that are not in the database.
     * @param newSkills - The set of skills that are new to the courseMate list.
     * @return A String containing the warning message.
     */
    public static String messageNewSkill(Set<Skill> newSkills) {
        if (newSkills.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("WARNING: the following skills has not been added to any other contacts, ")
                .append("please ensure it is not misspelt: ");
        int size = newSkills.size();
        int count = 0;
        for (Skill skill : newSkills) {
            sb.append(skill.toString());
            count++;
            if (count < size) {
                sb.append(", ");
            } else {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /** Retrieves the newly added skills not in the current courseMate list. */
    private Set<Skill> getNewSkills(Model model) {
        List<CourseMate> getCourseMateList = model.getContactList().getCourseMateList();
        Set<Skill> currentSkills = new HashSet<>();
        for (CourseMate courseMate : getCourseMateList) {
            Set<Skill> courseMateSkills = courseMate.getSkills();
            currentSkills.addAll(courseMateSkills);
        }

        Set<Skill> newSkills = new HashSet<>();
        Set<Skill> toAddSkill = toAdd.getSkills();
        for (Skill skill : toAddSkill) {
            if (!currentSkills.contains(skill)) {
                newSkills.add(skill);
            }
        }
        return newSkills;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddCommand)) {
            return false;
        }

        AddCommand otherAddCommand = (AddCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
