package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.Toolkit;
import java.awt.GraphicsEnvironment;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Copies the emails of all persons in the address book to the clipboard.
 */
public class CopyCommand extends Command {

    public static final String COMMAND_WORD = "copy";
    public static final String MESSAGE_SUCCESS = "Copied emails of all listed persons to clipboard";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (lastShownList.isEmpty()) {
            throw new CommandException("No person currently displayed");
        }
        // only copies to clipboard if the environment is not headless
        if (!GraphicsEnvironment.isHeadless()) {
            copyToClipboard(lastShownList);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Copies the emails of all persons in {@code lastShownList} to the clipboard.
     */
    private void copyToClipboard(List<Person> lastShownList) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringBuilder emails = new StringBuilder();

        for (Person person : lastShownList) {
            emails.append(person.getEmail().value).append(" ");
        }

        StringSelection stringSelection = new StringSelection(emails.toString().trim());
        clipboard.setContents(stringSelection, stringSelection);
    }
}
