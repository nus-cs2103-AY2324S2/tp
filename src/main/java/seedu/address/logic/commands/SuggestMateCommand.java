package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.HasRequiredSkillsPredicate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.skill.Skill;

public class SuggestMateCommand extends Command {
    public static final String COMMAND_WORD = "suggest-mate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all course mates with any of "
            + "the required skills from the given group and displays them as a list.\n"
            + "The course mates already in the group are not displayed. groups can be specified by name.\n"
            + "Parameters: NAME (cannot be empty and must already exist)\n"
            + "Example: " + COMMAND_WORD + "CS2103T";

    private final Name groupName;

    public SuggestMateCommand(Name groupName) {
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
                // return "all required skills have already been fulfilled. consider adding what skills you're looking for?"
            }

            HasRequiredSkillsPredicate predicate = new HasRequiredSkillsPredicate(memberList, uncompletedSkills);
            model.updateFilteredCourseMateList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_GROUPS_LISTED_OVERVIEW, model.getFilteredGroupList().size()));
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", groupName)
                .toString();
    }
}
