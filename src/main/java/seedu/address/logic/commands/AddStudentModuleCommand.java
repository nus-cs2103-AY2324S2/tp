package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.student.Student;

/**
 * Adds a student to the address book.
 */
public class AddStudentModuleCommand extends Command {

    public static final String COMMAND_WORD = "add_module";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module to the student in the address book. "
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_MODULE_CODE + "MODULE CODE";

    public static final String MESSAGE_SUCCESS = "New module %1$s added to student: %2$s";
    public static final String MESSAGE_DUPLICATE_MODULE =
            "This module already exists in the student's contact in address book";

    private final Index index;
    private final ModuleCode moduleCode;

    /**
     * Creates an AddStudentModuleCommand to add the specified {@code Module} to {@code Student}
     */
    public AddStudentModuleCommand(Index index, ModuleCode moduleCode) {
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

        if (model.doesStudentHaveModule(studentToModify, moduleCode)) {
            throw new CommandException(MESSAGE_DUPLICATE_MODULE);
        }

        model.addModuleToStudent(moduleCode, studentToModify);

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
        if (!(other instanceof AddStudentModuleCommand)) {
            return false;
        }

        AddStudentModuleCommand otherAddCommand = (AddStudentModuleCommand) other;
        return index.equals(otherAddCommand.index) && moduleCode.equals(otherAddCommand.moduleCode);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student index to add module to", index)
                .add("module to add to student", moduleCode)
                .toString();
    }
}

