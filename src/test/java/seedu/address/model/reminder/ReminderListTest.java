package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;



public class ReminderListTest {
    @Test
    public void toStringMethodLastMet() {
        ObservableList<Person> remindersList = FXCollections.observableArrayList();
        Person person1 = new PersonBuilder().withLastMet(LocalDate.of(2001, 1, 1)).build();
        remindersList.add(person1);
        Person person2 = new PersonBuilder().withLastMet(LocalDate.of(2002, 2, 2)).build();
        remindersList.add(person2);
        ReminderList reminderList = new ReminderList(ReminderType.LAST_MET, remindersList);
        assertEquals(reminderList.toString(), person1.overdueLastMetStringFormat()
                + "\n" + person2.overdueLastMetStringFormat() + "\n");
    }

    @Test
    public void toStringMethodSchedule() {
        ObservableList<Person> remindersList = FXCollections.observableArrayList();
        Person person1 = new PersonBuilder().withSchedule(LocalDateTime.now().plusDays(10), false).build();
        remindersList.add(person1);
        Person person2 = new PersonBuilder().withSchedule(LocalDateTime.now().plusDays(5), false).build();
        remindersList.add(person2);
        ReminderList reminderList = new ReminderList(ReminderType.SCHEDULES, remindersList);
        assertEquals(reminderList.toString(), person1.scheduleStringFormat()
                + "\n" + person2.scheduleStringFormat() + "\n");
    }
}
