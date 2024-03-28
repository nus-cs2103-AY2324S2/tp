package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.HasRequiredSkillsPredicate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.skill.Skill;

/**
 * Finds and lists all course mates in contact list whose skills match with uncompleted skills
 * in the queried group. Course mates already in the group will not be listed.
 */
public class SuggestMateCommand extends Command {
    public static final String COMMAND_WORD = "suggest-mate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all course mates with any of "
            + "the required skills from the given group and displays them as a list.\n"
            + "The course mates already in the group are not displayed. Groups can be specified by name.\n"
            + "Parameters: NAME (cannot be empty and must already exist)\n"
            + "Example: " + COMMAND_WORD + " CS2103T";

    public static final String MESSAGE_NO_UNCOMPLETED_SKILLS = "All required skills have already been fulfilled. "
            + "Consider adding what skills you're looking for?";

    private final Name groupName;

    /**
     * Public constructor for SuggestMateCommand
     * @param groupName - the name of the group
     */
    public SuggestMateCommand(Name groupName) {
        requireNonNull(groupName);
        this.groupName = groupName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Group group = model.findGroup(groupName);

            List<CourseMate> memberList = group.asUnmodifiableObservableList();
            Set<Skill> uncompletedSkills = group.uncompletedSkills();

            if (uncompletedSkills.isEmpty()) {
                return new CommandResult(MESSAGE_NO_UNCOMPLETED_SKILLS);
            }

            HasRequiredSkillsPredicate predicate = new HasRequiredSkillsPredicate(memberList, uncompletedSkills);
            model.updateFilteredCourseMateList(predicate);
            return new CommandResult(String.format(Messages.MESSAGE_COURSE_MATES_LISTED_OVERVIEW,
                    model.getFilteredCourseMateList().size()));
        } catch (GroupNotFoundException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_NAME, exception);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SuggestMateCommand)) {
            return false;
        }

        SuggestMateCommand otherSuggestMateCommand = (SuggestMateCommand) other;
        return groupName.equals(otherSuggestMateCommand.groupName);
    }
}
