package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
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

        StringSelection emails = getEmails(lastShownList);
        // only copies to clipboard if the environment is not headless
        if (!GraphicsEnvironment.isHeadless()) {
            copyToClipboard(emails);
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    /**
     * Returns a StringSelection containing the emails of all persons in {@code lastShownList}.
     * @param lastShownList list of persons to get emails from.
     * @return StringSelection containing the emails of all persons in {@code lastShownList}.
     */
    public StringSelection getEmails(List<Person> lastShownList) {
        StringBuilder emails = new StringBuilder();

        for (Person person : lastShownList) {
            emails.append(person.getEmail().value).append("; ");
        }
        return new StringSelection(emails.toString().trim());
    }

    /**
     * Copies the emails of all persons in {@code lastShownList} to the clipboard.
     */
    private void copyToClipboard(StringSelection stringSelection) {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, stringSelection);
    }
}
