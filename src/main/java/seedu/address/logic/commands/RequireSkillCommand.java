package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.skill.Skill;

/**
 * Adds required skill(s) to a preexisting group.
 */
public class RequireSkillCommand extends Command {
    public static final String COMMAND_WORD = "require-skill";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds required skills to an already existing group. "
            + "Parameters: NAME (group must exist) "
            + PREFIX_SKILL + " SKILL "
            + "[" + PREFIX_SKILL + " SKILL" + "]\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_SKILL + " Python "
            + PREFIX_SKILL + " Java";
    public static final String MESSAGE_SUCCESFULLY_ADDED = "Group successfully modified, Name: %1$s\n"
            + "%2$s skills are now required for the group!";

    private final Name groupName;
    private final Set<Skill> skillSet;

    /**
     * Basic constructor for {@code RequireSkillCommand}.
     */
    public RequireSkillCommand(Name groupName, Set<Skill> skillSet) {
        requireAllNonNull(groupName, skillSet);
        this.groupName = groupName;
        this.skillSet = skillSet;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Group toModify;
        try {
            toModify = model.findGroup(groupName);
        } catch (GroupNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_NAME);
        }

        Set<Skill> modifiedSkillSet = new HashSet<>(toModify.getSkills());
        for (Skill skill: skillSet) {
            modifiedSkillSet.add(skill);
        }
        Group modifiedGroup = new Group(
                toModify.getName(),
                toModify.asUnmodifiableObservableList(),
                modifiedSkillSet,
                toModify.getTelegramChat());

        model.setGroup(toModify, modifiedGroup);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_SUCCESFULLY_ADDED, groupName,
                modifiedGroup.getSkills().size()), false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof RequireSkillCommand)) {
            return false;
        }

        RequireSkillCommand otherRequireSkillCommand = (RequireSkillCommand) other;
        return otherRequireSkillCommand.groupName.equals(groupName)
                && otherRequireSkillCommand.skillSet.equals(skillSet);
    }
}
