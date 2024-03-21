package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_COURSE_MATES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.ContainsKeywordPredicate;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.QueryableCourseMate;
import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.skill.Skill;

/**
 * Adds a courseMate to the contact list.
 */
public class DeleteSkillCommand extends Command {

    public static final String COMMAND_WORD = "delete-skill";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes skills of a coursemate. "
            + "NAME can be specified either by full name or by the '#' notation.\n"
            + "Parameters: NAME "
            + PREFIX_SKILL + " SKILL "
            + "[" + PREFIX_SKILL + " SKILL]...\n"
            + "Example: " + COMMAND_WORD + " #1 "
            + PREFIX_SKILL + " Python "
            + PREFIX_SKILL + " Java";

    public static final String MESSAGE_SUCCESS = "Skills are successfully deleted";
    public static final String MESSAGE_NOT_EDITED = "At least one skill should be provided.";
    public static final String MESSAGE_DUPLICATE_COURSE_MATE = "This courseMate already exists in the contact list";
    public static final String MESSAGE_SKILL_NOT_PRESENT = "This courseMate does not have one of the skills provided.";

    private final QueryableCourseMate queryableCourseMate;
    private final DeleteSkillDescriptor deleteSkillDescriptor;
    /**
     * @param index of the courseMate in the filtered courseMate list to edit
     * @param deleteSkillDescriptor list of skills to edit the courseMate with
     */
    public DeleteSkillCommand(QueryableCourseMate queryableCourseMate, DeleteSkillDescriptor deleteSkillDescriptor) {
        requireNonNull(queryableCourseMate);
        requireNonNull(deleteSkillDescriptor);

        this.queryableCourseMate = queryableCourseMate;
        this.deleteSkillDescriptor = new DeleteSkillDescriptor(deleteSkillDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<CourseMate> lastShownList = model.getFilteredCourseMateList();

        if (queryableCourseMate.isIndex()
                && queryableCourseMate.getIndex().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_DISPLAYED_INDEX);
        }

        List<CourseMate> courseMateToEditList;
        try {
            courseMateToEditList = model.findCourseMate(queryableCourseMate);
        } catch (CourseMateNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_COURSE_MATE_NAME);
        }

        //If there are more than 1 matching names
        if (courseMateToEditList.size() > 1) {
            ContainsKeywordPredicate predicate = new ContainsKeywordPredicate(
                    queryableCourseMate.getName().toString());
            model.updateFilteredCourseMateList(predicate);
            return new CommandResult(
                    String.format(Messages.MESSAGE_SIMILAR_COURSE_MATE_NAME,
                            model.getFilteredCourseMateList().size()), false, false, true);
        }

        CourseMate courseMateToEdit = courseMateToEditList.get(0);

        if (!validSkillsToDelete(courseMateToEdit, deleteSkillDescriptor)) {
            throw new CommandException(MESSAGE_SKILL_NOT_PRESENT);
        }
        assert(validSkillsToDelete(courseMateToEdit, deleteSkillDescriptor));
        CourseMate editedCourseMate = deleteCourseMateSkills(courseMateToEdit, deleteSkillDescriptor);

        if (!courseMateToEdit.isSameCourseMate(editedCourseMate) && model.hasCourseMate(editedCourseMate)) {
            throw new CommandException(MESSAGE_DUPLICATE_COURSE_MATE);
        }

        model.setCourseMate(courseMateToEdit, editedCourseMate);
        model.updateFilteredCourseMateList(PREDICATE_SHOW_ALL_COURSE_MATES);
        model.setRecentlyProcessedCourseMate(editedCourseMate);
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    /**
     * Checks if the {@code CourseMate} to be edited has all the skills listed in {@code deleteSkillDescriptor}.
     */
    private static boolean validSkillsToDelete(CourseMate courseMateToEdit,
                                               DeleteSkillDescriptor deleteSkillDescriptor) {
        Set<Skill> courseMateSkills = courseMateToEdit.getSkills();
        Set<Skill> commandSkills = deleteSkillDescriptor.getSkills().orElse(Collections.emptySet());

        for (Skill skill : commandSkills) {
            if (!courseMateSkills.contains(skill)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates and returns a {@code CourseMate} with the details of {@code courseMateToEdit}
     * edited with {@code deleteSkillDescriptor}.
     */
    private static CourseMate deleteCourseMateSkills(CourseMate courseMateToEdit,
                                                     DeleteSkillDescriptor deleteSkillDescriptor) {
        requireNonNull(courseMateToEdit);

        deleteSkillDescriptor.deleteSkills(courseMateToEdit.getSkills());
        Set<Skill> updatedSkills = deleteSkillDescriptor.getSkills().orElse(courseMateToEdit.getSkills());

        return new CourseMate(courseMateToEdit.getName(), courseMateToEdit.getPhone(),
                courseMateToEdit.getEmail(), updatedSkills);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteSkillCommand)) {
            return false;
        }

        DeleteSkillCommand otherDeleteSkillCommand = (DeleteSkillCommand) other;
        return queryableCourseMate.getIndex().equals(otherDeleteSkillCommand.queryableCourseMate.getIndex())
                && deleteSkillDescriptor.equals(otherDeleteSkillCommand.deleteSkillDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", queryableCourseMate.getIndex())
                .add("deleteSkillDescriptor", deleteSkillDescriptor)
                .toString();
    }

    /**
     * Stores the list of skills to delete from the courseMate.
     */
    public static class DeleteSkillDescriptor {
        private Set<Skill> skills;

        public DeleteSkillDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code skills} is used internally.
         */
        public DeleteSkillDescriptor(DeleteSkillDescriptor toCopy) {
            setSkills(toCopy.skills);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(skills);
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
         * Deletes the set of skills in the object with the set of skills in the argument
         */
        public void deleteSkills(Set<Skill> argSkills) {
            Set<Skill> newSet = new HashSet<Skill>();
            for (Skill skill : argSkills) {
                newSet.add(skill);
            }
            for (Skill skill : this.skills) {
                newSet.remove(skill);
            }
            setSkills(newSet);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteSkillDescriptor)) {
                return false;
            }

            DeleteSkillDescriptor otherDeleteSkillDescriptor = (DeleteSkillDescriptor) other;
            return Objects.equals(skills, otherDeleteSkillDescriptor.skills);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this).add("skills", skills).toString();
        }
    }
}
