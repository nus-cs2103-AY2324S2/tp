//package seedu.address.logic.commands;
//
//import static java.util.Objects.requireNonNull;
//
//import java.util.List;
//
//import javafx.collections.ObservableList;
//import seedu.address.commons.core.index.Index;
//import seedu.address.commons.util.ToStringBuilder;
//import seedu.address.logic.Messages;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.Model;
//import seedu.address.model.person.Person;
//
///**
// * Views the details and policies of an existing person in the address book.
// */
//public class ReminderCommand extends Command {
//
//    public static final String COMMAND_WORD = "remind";
//    public static final String MESSAGE_USAGE = COMMAND_WORD
//            + ": Refresh the reminder list.\n"
//            + "Example: " + COMMAND_WORD;
//    public static final String MESSAGE_VIEW_PERSON_SUCCESS = "Reminder list refreshed";
//
//    /**
//     * Creates a {@code ViewCommand} to view a Person at the specified {@code targetIndex}.
//     */
//    @Override
//    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        model.
//        return new CommandResult(MESSAGE_VIEW_PERSON_SUCCESS);
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof ViewCommand)) {
//            return false;
//        }
//
//        ViewCommand otherViewCommand = (ViewCommand) other;
//        return targetIndex.equals(otherViewCommand.targetIndex);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("targetIndex", targetIndex)
//                .toString();
//    }
//}
