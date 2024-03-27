package seedu.internhub.logic.commands;

import java.util.function.Predicate;

import seedu.internhub.model.Model;
import seedu.internhub.model.person.Person;
/**
 * Filters list to upcoming interviews in the next 'n' days
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_SUCCESS = "Listed applications that are due or have interviews in %1$d days.";

    private int numberOfDays;

    public ReminderCommand(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public CommandResult execute(Model model) {
        // Define a predicate to filter persons with interviews within N days
        Predicate<Person> isWithinNDaysPredicate = person ->
                person.getInterviewDate() != null && person.getInterviewDate().isWithinNDays(numberOfDays);

        // Update the filtered list in the model using a predicate
        model.updateFilteredPersonList(isWithinNDaysPredicate);

        return new CommandResult(String.format(MESSAGE_SUCCESS, numberOfDays));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReminderCommand);
    }
}
