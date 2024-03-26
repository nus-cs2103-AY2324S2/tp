package seedu.edulink.logic.commands;

import static seedu.edulink.logic.parser.CliSyntax.PREFIX_FILENAME;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.edulink.logic.parser.CliSyntax.PREFIX_TAG;

import java.io.IOException;
import java.util.List;

import seedu.edulink.commons.util.CsvUtil;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;
import seedu.edulink.model.student.Student;

public class ExportCommand extends Command{

    public static final String COMMAND_WORD = "export";
    public static final String EXPORT_FORMAT = ".csv";

    private final String fileName;
    public static final String MESSAGE_EXPORT_SUCCESS = "Exported Data to the file - ";
    public static final String MESSAGE_EXPORT_FAILURE = "Unable to Export data to the destination";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD + " " + PREFIX_FILENAME + "FILENAME";

    public ExportCommand(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        List<Student> studentsList= model.getFilteredPersonList();
        try {
            CsvUtil.convertToCSV(studentsList, this.fileName + EXPORT_FORMAT);
        } catch (IOException e) {
            throw new CommandException(e.toString());
        }
        return new CommandResult(MESSAGE_EXPORT_SUCCESS + fileName + ".csv");
    }
}
