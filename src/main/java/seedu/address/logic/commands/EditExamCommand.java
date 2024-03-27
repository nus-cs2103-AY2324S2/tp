package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.Score;


/**
 * Edits the details of an existing exam in the exam book.
 */
public class EditExamCommand extends Command {

    public static final String COMMAND_WORD = "editExam";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the exam identified "
            + "by the index number used in the displayed exam list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[EXAM DETAILS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NAME + "Midterm";

    public static final String MESSAGE_EDIT_EXAM_SUCCESS = "Edited Exam: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EXAM = "This exam already exists in the exam list";

    private final Index index;
    private final EditExamDescriptor editExamDescriptor;

    /**
     * @param index of the exam in the filtered exam list to edit
     * @param editExamDescriptor details to edit the exam with
     */
    public EditExamCommand(Index index, EditExamDescriptor editExamDescriptor) {
        requireNonNull(index);
        requireNonNull(editExamDescriptor);

        this.index = index;
        this.editExamDescriptor = new EditExamDescriptor(editExamDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Exam> lastShownList = model.getExamList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EXAM_DISPLAYED_INDEX);
        }

        Exam examToEdit = lastShownList.get(index.getZeroBased());
        Exam editedExam = createEditedExam(examToEdit, editExamDescriptor);

        if (!examToEdit.isSameExam(editedExam) && model.hasExam(editedExam)) {
            throw new CommandException(MESSAGE_DUPLICATE_EXAM);
        }

        model.setExam(examToEdit, editedExam);
        return new CommandResult(String.format(MESSAGE_EDIT_EXAM_SUCCESS, Messages.format(editedExam)));
    }

    /**
     * Creates and returns a {@code Exam} with the details of {@code examToEdit}
     * edited with {@code editExamDescriptor}.
     */
    private static Exam createEditedExam(Exam examToEdit, EditExamDescriptor editExamDescriptor) {
        assert examToEdit != null;

        String updatedName = editExamDescriptor.getName().orElse(examToEdit.getName());
        Score updatedMaxScore = editExamDescriptor.getMaxScore().orElse(examToEdit.getMaxScore());

        return new Exam(updatedName, updatedMaxScore);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditExamCommand)) {
            return false;
        }

        EditExamCommand otherEditExamCommand = (EditExamCommand) other;
        return index.equals(otherEditExamCommand.index)
                && editExamDescriptor.equals(otherEditExamCommand.editExamDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editExamDescriptor", editExamDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the exam with. Each non-empty field value will replace the
     * corresponding field value of the exam.
     */
    public static class EditExamDescriptor {
        private String name;
        private Score maxScore;

        public EditExamDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditExamDescriptor(EditExamDescriptor toCopy) {
            setName(toCopy.name);
            setMaxScore(toCopy.maxScore);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, maxScore);
        }

        public void setName(String name) {
            this.name = name;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public void setMaxScore(Score maxScore) {
            this.maxScore = maxScore;
        }

        public Optional<Score> getMaxScore() {
            return Optional.ofNullable(maxScore);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            if (!(other instanceof EditExamDescriptor)) {
                return false;
            }

            EditExamDescriptor e = (EditExamDescriptor) other;

            return getName().equals(e.getName())
                    && getMaxScore().equals(e.getMaxScore());
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("maxScore", maxScore)
                    .toString();
        }
    }

}
