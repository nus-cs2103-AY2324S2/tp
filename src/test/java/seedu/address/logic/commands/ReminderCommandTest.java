//package seedu.address.logic.commands;
//
//import org.junit.jupiter.api.Test;
//import seedu.address.model.Model;
//import seedu.address.model.ModelManager;
//import seedu.address.model.UserPrefs;
//import seedu.address.model.person.InterviewDate;
//import seedu.address.model.person.Person;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
//import static seedu.address.logic.commands.ReminderCommand.MESSAGE_SUCCESS;
//import static seedu.address.logic.commands.ReminderCommandParser.INVALID_NUMBER_OF_DAYS;
//import static seedu.address.logic.commands.ReminderCommandParser.NEGATIVE_NUMBER_OF_DAYS;
//import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
//
//public class ReminderCommandTest {
//
//    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
//
//    @Test
//    public void execute_validNumberOfDays_success() {
//        List<Person> persons = new ArrayList<>();
//        persons.add(new Person("Alice", new InterviewDate(LocalDate.now().plusDays(2))));
//        persons.add(new Person("Bob", new InterviewDate(LocalDate.now().plusDays(3))));
//        persons.add(new Person("Charlie", new InterviewDate(LocalDate.now().plusDays(4))));
//
//        Model model = new ModelManager();
//        model.getAddressBook().addAll(persons);
//
//        ReminderCommand command = new ReminderCommand(5);
//        CommandResult result = command.execute(model);
//
//        assertEquals(3, model.getFilteredPersonList().size());
//        assertEquals(String.format(MESSAGE_SUCCESS, 5), result.getFeedbackToUser());
//    }
//
//    @Test
//    public void execute_zeroNumberOfDays_errorMessage() {
//        Model model = new ModelManager();
//
//        ReminderCommand command = new ReminderCommand(0);
//        CommandResult result = command.execute(model);
//
//        assertEquals(0, model.getFilteredPersonList().size());
//        assertEquals(INVALID_NUMBER_OF_DAYS, result.getFeedbackToUser());
//    }
//
//    @Test
//    public void execute_negativeNumberOfDays_errorMessage() {
//        Model model = new ModelManager();
//
//        ReminderCommand command = new ReminderCommand(-1);
//        CommandResult result = command.execute(model);
//
//        assertEquals(0, model.getFilteredPersonList().size());
//        assertEquals(NEGATIVE_NUMBER_OF_DAYS, result.getFeedbackToUser());
//    }
//}
