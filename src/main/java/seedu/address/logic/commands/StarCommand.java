package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STAR;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Star;
import seedu.address.model.student.Student;

/**
 * Adds a star to a student in the address book.
 * @code star 1 s/3
 */
public class StarCommand extends Command {

    public static final String COMMAND_WORD = "star";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_STAR + "[STAR]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_STAR + "3";

    public static final String MESSAGE_ADD_STAR_SUCCESS = "Added stars to Student: %1$s";

    private final Index index; // Index to give star
    private final Star star; // Number of stars given

    /**
     * @param index of the student in the filtered student list to give the star.
     * @param star number of stars given to student.
     */
    public StarCommand(Index index, Star star) {
        requireNonNull(index);
        requireNonNull(star);
        this.index = index;
        this.star = star;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException { // executes the starring
        List<Student> lastShownList = model.getCorrectStudentList(); // get the list of students

        if (index.getZeroBased() >= lastShownList.size()) { // if index out of range
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased()); // get Student indexed
        Star starToEdit = studentToEdit.getStar(); // return the stars
        Star edittedStar = new Star(starToEdit.numOfStars + this.star.numOfStars); // new Star to be added

        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getMajor(), edittedStar, studentToEdit.getBolt(), studentToEdit.getTags());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_ADD_STAR_SUCCESS, Messages.format(editedStudent)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StarCommand)) {
            return false;
        }

        StarCommand e = (StarCommand) other;
        return index.equals(e.index);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", this.index)
                .add("star", this.star)
                .toString();
    }
}
