package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.TelegramChat;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * Edits the telegram chat of a preexisting group.
 */
public class EditGroupCommand extends Command {
    public static final String COMMAND_WORD = "edit-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the telegram chat of a preexisting group. "
            + "groups can be specified by name.\n"
            + "Parameters: NAME (cannot be empty and must already exist) "
            + PREFIX_TELEGRAM + " TELEGRAM_CHAT_URL (cannot be empty)\n"
            + "Example: " + COMMAND_WORD + " CS2103T GROUP "
            + PREFIX_TELEGRAM + "https://t.me/+3Jh9eXVeRh7qoaIN";

    private final Name toModify;
    private final TelegramChat telegramChat;

    /**
     * Basic constructor for {@code EditGroupCommand}. Edits the telegram chat of a specified group.
     * @param toModify group to be edited
     * @param telegramChat new telegram chat
     */
    public EditGroupCommand(Name toModify, TelegramChat telegramChat) {
        requireNonNull(toModify);
        this.toModify = toModify;
        this.telegramChat = telegramChat;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        try {
            Group groupToEdit = model.findGroup(toModify);
            Group editedGroup = new Group(groupToEdit.getName(),
                    groupToEdit.asUnmodifiableObservableList(), groupToEdit.getSkills(), telegramChat);

            model.setGroup(groupToEdit, editedGroup);

            return new CommandResult(String.format("Edited group %s",
                    toModify));
        } catch (GroupNotFoundException exception) {
            throw new CommandException(Messages.MESSAGE_INVALID_GROUP_NAME, exception);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EditGroupCommand)) {
            return false;
        }

        EditGroupCommand otherEdit = (EditGroupCommand) other;
        return otherEdit.toModify.equals(toModify)
                && otherEdit.telegramChat.equals(telegramChat);
    }
}
