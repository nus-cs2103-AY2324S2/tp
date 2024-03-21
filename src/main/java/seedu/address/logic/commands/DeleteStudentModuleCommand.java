package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.student.Student;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

/**
 * Deletes a student from the address book.
 */
public class DeleteStudentModuleCommand extends Command {

    public static final String COMMAND_WORD = "delete_module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a module from a student in the address book. "
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_MODULE_CODE + "MODULE CODE";

    public static final String MESSAGE_SUCCESS = "Module %1$s deleted from student: %2$s";
    public static final String MESSAGE_MODULE_NOT_FOUND =
            "This module does not exist in the student's contact in address book";

    private final Index index;
    private final ModuleCode moduleCode;

    /**
     * Creates an DeleteStudentModuleCommand to delete the specified {@code ModuleCode} from student at {@code Index}
     */
    public DeleteStudentModuleCommand(Index index, ModuleCode moduleCode) {
        requireNonNull(index);
        requireNonNull(moduleCode);
        this.index = index;
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToModify = lastShownList.get(index.getZeroBased());

        if (!model.doesStudentHaveModule(studentToModify, moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

//        model.addModuleToStudent(moduleCode, studentToModify);

        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        moduleCode.getCode(),
                        Messages.format(studentToModify)
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStudentModuleCommand)) {
            return false;
        }

        DeleteStudentModuleCommand otherDeleteCommand = (DeleteStudentModuleCommand) other;
        return index.equals(otherDeleteCommand.index) && moduleCode.equals(otherDeleteCommand.moduleCode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student index to delete module from", index)
                .add("module to delete from student", moduleCode)
                .toString();
    }
}

