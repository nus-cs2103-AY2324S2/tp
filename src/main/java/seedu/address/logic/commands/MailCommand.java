package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import static java.util.Objects.requireNonNull;

import seedu.address.model.person.TagContainsKeywordsPredicate;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Lists all email of contacts associated with the tag.
 */
public class MailCommand extends Command {

    public static final String COMMAND_WORD = "mail";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists the email of contacts containing the inputted tag\n"
            + "Parameters: TAG\n"
            + "Example: " + COMMAND_WORD + "friends";

    public static final String MESSAGE_EMAIL_CONTACT_SUCCESS = "Here is the list of emails: %1$s";

    private final TagContainsKeywordsPredicate predicate;

    public MailCommand(TagContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        requireNonNull(history);

        model.updateFilteredPersonList(predicate);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < model.getFilteredPersonList().size(); i++) {
            builder.append(model.getFilteredPersonList().get(i).getEmail()).append(",");
        }
        try {
            Desktop desktop;
            if (Desktop.isDesktopSupported()
                    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                URI mailto = new URI("mailto:" + builder);
                desktop.mail(mailto);
            } else {
                throw new RuntimeException("desktop doesn't support mailto");
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
        return new CommandResult(
                String.format(MESSAGE_EMAIL_CONTACT_SUCCESS, builder));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MailCommand)) {
            return false;
        }

        MailCommand otherMailCommand = (MailCommand) other;
        return predicate.equals(otherMailCommand.predicate);
    }
}
