package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.exam.Exam;


/**
 * Selects an exam from the address book.
 */
public class SelectExamCommand extends Command {

    public static final String COMMAND_WORD = "selectExam";

    public static final String MESSAGE_USAGE = "selectExam: Selects the exam by its name. "
        + "Parameters: " + PREFIX_NAME + " NAME\n"
        + "Example: selectExam " + PREFIX_NAME + "Midterm";

    public static final String MESSAGE_SUCCESS = "Exam selected: %1$s";
    public static final String MESSAGE_EXAM_NOT_FOUND = "Exam not found";

    private final String targetExamName;

    /**
     * Creates a SelectExamCommand to select the specified {@code Exam}
     */
    public SelectExamCommand(String examName) {
        this.targetExamName = examName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Exam> examList = model.getExamList();
        if (examList == null || examList.isEmpty()) {
            throw new CommandException(MESSAGE_EXAM_NOT_FOUND);
        }
        for (Exam exam : examList) {
            if (exam.getName().equals(targetExamName)) {
                model.selectExam(exam);
                return new CommandResult(String.format(MESSAGE_SUCCESS, exam));
            }
        }

        throw new CommandException(MESSAGE_EXAM_NOT_FOUND);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectExamCommand)) {
            return false;
        }

        SelectExamCommand otherSelectExamCommand = (SelectExamCommand) other;
        return targetExamName.equals(otherSelectExamCommand.targetExamName);
    }
}
