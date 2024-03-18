package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.skill.Skill;

/**
 * Adds a courseMate to the contact list.
 */
public class AddSkillCommand extends Command {

    public static final String COMMAND_WORD = "add-skill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a skill to a coursemate. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SKILL + " SKILL "
            + "[" + PREFIX_SKILL + " SKILL]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SKILL + " Python "
            + PREFIX_SKILL + " Java";

    public static final String MESSAGE_SUCCESS = "New skill added";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "This courseMate already exists in the contact list";

    public static String MESSAGE_NEW_SKILL(Set<Skill> skills) {
        return "WARNING: The competency " + Skill.getSkills(skills);
                + " has not been added to any other contacts, please ensure it is not misspelt.";
    }

    private final Index index;
    private final SkillDescriptor skillDescriptor;
    /**
     * @param index of the courseMate in the filtered courseMate list to edit
     * @param editCourseMateDescriptor details to edit the courseMate with
     */
    public AddSkillCommand(Index index, SkillDescriptor skillDescriptor) {
        requireNonNull(index);
        requireNonNull(skillDescriptor);

        this.index = index;
        this.skillDescriptor = new SkillDescriptor(skillDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> lastShownList = model.getFilteredCourseMateList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        }

        CourseMate courseMateToEdit = lastShownList.get(index.getZeroBased());
        CourseMate editedCourseMate = createEditedCourseMate(courseMateToEdit, editCourseMateDescriptor);

        if (!courseMateToEdit.isSameCourseMate(editedCourseMate) && model.hasCourseMate(editedCourseMate)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_MATE);
        }

        model.setCourseMate(courseMateToEdit, editedCourseMate);
        model.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
        model.setRecentlyProcessedCourseMate(editedCourseMate);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    /**
     * Creates and returns a {@code CourseMate} with the details of {@code courseMateToEdit}
     * edited with {@code editCourseMateDescriptor}.
     */
    private static CourseMate createEditedCourseMate(CourseMate courseMateToEdit,
                                                     EditCourseMateDescriptor editCourseMateDescriptor) {
        assert courseMateToEdit != null;

        Name updatedName = editCourseMateDescriptor.getName().orElse(courseMateToEdit.getName());
        Phone updatedPhone = editCourseMateDescriptor.getPhone().orElse(courseMateToEdit.getPhone());
        Email updatedEmail = editCourseMateDescriptor.getEmail().orElse(courseMateToEdit.getEmail());
        Set<Skill> updatedSkills = editCourseMateDescriptor.getSkills().orElse(courseMateToEdit.getSkills());

        return new CourseMate(updatedName, updatedPhone, updatedEmail, updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSkillCommand)) {
            return false;
        }

        AddSkillCommand otherAddSkillCommand = (AddSkillCommand) other;
        return index.equals(otherAddSkillCommand.index)
                && skillDescriptor.equals(otherAddSkillCommand.skillDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("otherSkillDescriptor", otherSkillDescriptor)
                .toString();
    }

    /**
     * Stores the list of skills to edit the courseMate with.
     */
    public static class SkillDescriptor {
        private Set<Skill> skills;

        public SkillDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public SkillDescriptor(SkillDescriptor toCopy) {
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(skills);
        }

        /**
         * Sets {@code skills} to this object's {@code skills}.
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

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SkillDescriptor)) {
                return false;
            }

            SkillDescriptor otherSkillDescriptor = (SkillDescriptor) other;
            return Objects.equals(skills, otherSkillDescriptor.skills);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).add("skills", skills).toString();
        }
    }
}
