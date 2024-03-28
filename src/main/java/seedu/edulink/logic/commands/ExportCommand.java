package seedu.edulink.logic.commands;

import static seedu.edulink.logic.parser.CliSyntax.PREFIX_FILENAME;

import java.io.IOException;
import java.util.List;

import seedu.edulink.commons.util.CsvUtil;
import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Student;

/**
 * Exports the Student data in a .csv File
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";
    public static final String EXPORT_FORMAT = ".csv";
    public static final String MESSAGE_EXPORT_SUCCESS = "Exported Data to the file - ";
    public static final String MESSAGE_EXPORT_FAILURE = "Unable to Export data to the destination";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_FILENAME + "FILENAME";

    private final String fileName;

    public ExportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        List<Student> studentsList = model.getFilteredPersonList();
        try {
            CsvUtil.convertToCsv(studentsList, this.fileName + EXPORT_FORMAT);
        } catch (IOException e) {
            throw new CommandException(MESSAGE_EXPORT_FAILURE);
        }
        return new CommandResult(MESSAGE_EXPORT_SUCCESS + fileName + ".csv");
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ExportCommand)) {
            return false;
        }

        ExportCommand otherExportCommand = (ExportCommand) other;
        return this.fileName.equalsIgnoreCase(otherExportCommand.fileName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("filename", fileName)
            .toString();
    }
}
