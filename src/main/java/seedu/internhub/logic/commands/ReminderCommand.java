package seedu.internhub.logic.commands;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.internhub.model.Model;
import seedu.internhub.model.person.Person;
/**
 * Filters list to upcoming interviews in the next 3 days
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_USAGE = "Reminder should not have any arguments.";
    public static final String MESSAGE_SUCCESS = "Listed applications that are due or have interviews in 3 days.";

    @Override
    public CommandResult execute(Model model) {
        // Define a predicate to filter persons with interviews within 3 days
        Predicate<Person> isWithin3DaysPredicate = person ->
                person.getInterviewDate() != null && person.getInterviewDate().isWithin3Days();

        // Get all persons from the model
        List<Person> allPersons = model.getAddressBook().getPersonList();

        // Filter the persons using the predicate
        List<Person> reminderList = allPersons.stream()
                .filter(isWithin3DaysPredicate)
                .collect(Collectors.toList());

        // Update the filtered list in the model using a predicate
        model.updateFilteredPersonList(isWithin3DaysPredicate);

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ReminderCommand);
    }
}
