package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_GROUPS;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.exceptions.GroupNotFoundException;
import seedu.address.model.skill.Skill;


/**
 * Marks certain skills in a group to be important
 */
public class MarkImportantCommand extends Command {
    public static final String COMMAND_WORD = "mark-important";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks a group skill to important. "
            + "Parameters: GROUP_NAME"
            + "[" + PREFIX_SKILL + " SKILL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SKILL + " Python "
            + PREFIX_SKILL + " Java";

    public static final String MESSAGE_SUCCESS = "Skills successfully marked as important";
    public static final String MESSAGE_SKILL_NOT_PRESENT = "This group does not have one of the skills provided.";

    public static final String MESSAGE_NOT_EDITED = "At least one skill to mark must be provided.";
    private final Name groupName;

    private final MarkImportantDescriptor markImportantDescriptor;

    /**
     * Creates a MarkImportantCommand that edits the group to have important skills
     * @param groupName the name of the group whose skills we want to mark
     */
    public MarkImportantCommand(Name groupName, MarkImportantDescriptor markImportantDescriptor) {
        requireNonNull(groupName);
        requireNonNull(markImportantDescriptor);
        this.groupName = groupName;
        this.markImportantDescriptor = new MarkImportantDescriptor(markImportantDescriptor);
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

        if (!validSkillsToMark(toModify, markImportantDescriptor)) {
            throw new CommandException(MESSAGE_SKILL_NOT_PRESENT);
        }

        Set<Skill> skills = markImportantDescriptor.replaceSkills(toModify.getSkills());
        Group modifiedGroup = new Group(toModify.getName(), toModify.asUnmodifiableObservableList(),
                skills, toModify.getTelegramChat());

        model.setGroup(toModify, modifiedGroup);
        model.updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, groupName, false, false, true));
    }

    /**
     * Checks whether the skills that wants to be marked are in the group
     */
    private static boolean validSkillsToMark(Group toModify,
                                               MarkImportantCommand.MarkImportantDescriptor markImportantDescriptor) {
        Set<Skill> courseMateSkills = toModify.getSkills();
        Set<Skill> commandSkills = markImportantDescriptor.getSkills().orElse(Collections.emptySet());

        for (Skill skill : commandSkills) {
            if (!courseMateSkills.contains(skill)) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkImportantCommand)) {
            return false;
        }

        MarkImportantCommand otherMarkImportantCommand = (MarkImportantCommand) other;
        return groupName.equals(otherMarkImportantCommand.groupName)
                && markImportantDescriptor.equals(otherMarkImportantCommand.markImportantDescriptor);
    }

    /**
     * Stores the list of skills to mark important in the group
     */
    public static class MarkImportantDescriptor {
        private Set<Skill> skills;

        public MarkImportantDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public MarkImportantDescriptor(MarkImportantDescriptor toCopy) {
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return (skills == null || skills.size() == 0) ? false : true;
        }

        /**
         * Adds {@code skills} to this object's {@code skills}.
         * A defensive copy of {@code skills} is used internally.
         */
        public void setSkills(Set<Skill> skills) {
            this.skills = (skills != null) ? new HashSet<>(skills) : null;
        }

        /**
         * Returns an unmodifiable skill set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code skills} is null.
         */
        public Optional<Set<Skill>> getSkills() {
            return (skills != null) ? Optional.of(Collections.unmodifiableSet(skills)) : Optional.empty();
        }

        /**
         * Adds all the skills given in the parameters that are not within the original set of skills
         */
        public Set<Skill> replaceSkills(Set<Skill> argSkills) {
            for (Skill skill : argSkills) {
                if (!skills.contains(skill)) {
                    skills.add(skill);
                }
            }
            return skills;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof MarkImportantCommand.MarkImportantDescriptor)) {
                return false;
            }

            MarkImportantCommand.MarkImportantDescriptor otherMarkImportantDescriptor =
                    (MarkImportantCommand.MarkImportantDescriptor) other;
            return Objects.equals(skills, otherMarkImportantDescriptor.skills);
        }
    }
}
