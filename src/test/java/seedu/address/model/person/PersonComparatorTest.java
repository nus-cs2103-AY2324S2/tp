package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonComparatorTest {
    @Test
    public void getComparator() {
        // sort by name
        Person alice = new PersonBuilder().withName("Alice").build();
        Person aliceCopy = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        // ascending
        Comparator<Person> nameAscComparator = PersonComparator.getComparator(SortCriteria.NAME, SortOrder.ASC);
        assertTrue(nameAscComparator.compare(alice, bob) < 0);
        assertEquals(0, nameAscComparator.compare(alice, aliceCopy));
        // descending
        Comparator<Person> nameDescComparator = PersonComparator.getComparator(SortCriteria.NAME, SortOrder.DESC);
        assertTrue(nameDescComparator.compare(alice, bob) > 0);
        assertEquals(0, nameDescComparator.compare(alice, aliceCopy));

        // sort by phone
        Person phone1 = new PersonBuilder().withPhone("12345678").build();
        Person phone1Copy = new PersonBuilder().withPhone("12345678").build();
        Person phone2 = new PersonBuilder().withPhone("87654321").build();
        // ascending
        Comparator<Person> phoneAscComparator = PersonComparator.getComparator(SortCriteria.PHONE, SortOrder.ASC);
        assertTrue(phoneAscComparator.compare(phone1, phone2) < 0);
        assertEquals(0, phoneAscComparator.compare(phone1, phone1Copy));
        // descending
        Comparator<Person> phoneDescComparator = PersonComparator.getComparator(SortCriteria.PHONE, SortOrder.DESC);
        assertTrue(phoneDescComparator.compare(phone1, phone2) > 0);
        assertEquals(0, phoneDescComparator.compare(phone1, phone1Copy));

        // sort by email
        Person email1 = new PersonBuilder().withEmail("abc@email.com").build();
        Person email1Copy = new PersonBuilder().withEmail("abc@email.com").build();
        Person email2 = new PersonBuilder().withEmail("bcd@email.com").build();
        // ascending
        Comparator<Person> emailAscComparator = PersonComparator.getComparator(SortCriteria.EMAIL, SortOrder.ASC);
        assertTrue(emailAscComparator.compare(email1, email2) < 0);
        assertEquals(0, emailAscComparator.compare(email1, email1Copy));
        // descending
        Comparator<Person> emailDescComparator = PersonComparator.getComparator(SortCriteria.EMAIL, SortOrder.DESC);
        assertTrue(emailDescComparator.compare(email1, email2) > 0);
        assertEquals(0, emailDescComparator.compare(email1, email1Copy));

        // sort by address
        Person address1 = new PersonBuilder().withAddress("123, Clementi Ave 3").build();
        Person address1Copy = new PersonBuilder().withAddress("123, Clementi Ave 3").build();
        Person address2 = new PersonBuilder().withAddress("456, Clementi Ave 4").build();
        // ascending
        Comparator<Person> addressAscComparator = PersonComparator.getComparator(SortCriteria.ADDRESS, SortOrder.ASC);
        assertTrue(addressAscComparator.compare(address1, address2) < 0);
        assertEquals(0, addressAscComparator.compare(address1, address1Copy));
        // descending
        Comparator<Person> addressDescComparator = PersonComparator.getComparator(SortCriteria.ADDRESS, SortOrder.DESC);
        assertTrue(addressDescComparator.compare(address1, address2) > 0);
        assertEquals(0, addressDescComparator.compare(address1, address1Copy));

        // sort by priority
        Person priority1 = new PersonBuilder().withPriority("low").build();
        Person priority1Copy = new PersonBuilder().withPriority("low").build();
        Person priority2 = new PersonBuilder().withPriority("high").build();
        // ascending
        Comparator<Person> priorityAscComparator = PersonComparator.getComparator(SortCriteria.PRIORITY, SortOrder.ASC);
        assertTrue(priorityAscComparator.compare(priority1, priority2) < 0);
        assertEquals(0, priorityAscComparator.compare(priority1, priority1Copy));
        // descending
        Comparator<Person> priorityDescComparator = PersonComparator.getComparator(SortCriteria.PRIORITY,
                SortOrder.DESC);
        assertTrue(priorityDescComparator.compare(priority1, priority2) > 0);
        assertEquals(0, priorityDescComparator.compare(priority1, priority1Copy));

        // sort by birthday
        Person birthday1 = new PersonBuilder().withBirthday("2021-01-01").build();
        Person birthday1Copy = new PersonBuilder().withBirthday("2021-01-01").build();
        Person birthday2 = new PersonBuilder().withBirthday("2021-01-02").build();
        // ascending
        Comparator<Person> birthdayAscComparator = PersonComparator.getComparator(SortCriteria.BIRTHDAY, SortOrder.ASC);
        assertTrue(birthdayAscComparator.compare(birthday1, birthday2) < 0);
        assertEquals(0, birthdayAscComparator.compare(birthday1, birthday1Copy));
        // descending
        Comparator<Person> birthdayDescComparator = PersonComparator.getComparator(SortCriteria.BIRTHDAY,
                SortOrder.DESC);
        assertTrue(birthdayDescComparator.compare(birthday1, birthday2) > 0);
        assertEquals(0, birthdayDescComparator.compare(birthday1, birthday1Copy));

//        // sort by last met
//        Person lastMet1 = new PersonBuilder().withLastMet("2021-01-01").build();
//        Person lastMet1Copy = new PersonBuilder().withLastMet("2021-01-01").build();
//        Person lastMet2 = new PersonBuilder().withLastMet("2021-01-02").build();
//        // ascending
//        Comparator<Person> lastMetAscComparator = PersonComparator.getComparator(SortCriteria.LASTMET, SortOrder.ASC);
//        assertTrue(lastMetAscComparator.compare(lastMet1, lastMet2) < 0);
//        assertEquals(0, lastMetAscComparator.compare(lastMet1, lastMet1Copy));
//        // descending
//        Comparator<Person> lastMetDescComparator = PersonComparator.getComparator(SortCriteria.LASTMET,
//                SortOrder.DESC);
//        assertTrue(lastMetDescComparator.compare(lastMet1, lastMet2) > 0);
//        assertEquals(0, lastMetDescComparator.compare(lastMet1, lastMet1Copy));
//
//        // sort by schedule
//        Person schedule1 = new PersonBuilder().withSchedule("2021-01-01").build();
//        Person schedule1Copy = new PersonBuilder().withSchedule("2021-01-01").build();
//        Person schedule2 = new PersonBuilder().withSchedule("2021-01-02").build();
//        // ascending
//        Comparator<Person> scheduleAscComparator = PersonComparator.getComparator(SortCriteria.SCHEDULE, SortOrder.ASC);
//        assertTrue(scheduleAscComparator.compare(schedule1, schedule2) < 0);
//        assertEquals(0, scheduleAscComparator.compare(schedule1, schedule1Copy));
//        // descending
//        Comparator<Person> scheduleDescComparator = PersonComparator.getComparator(SortCriteria.SCHEDULE,
//                SortOrder.DESC);
//        assertTrue(scheduleDescComparator.compare(schedule1, schedule2) > 0);
//        assertEquals(0, scheduleDescComparator.compare(schedule1, schedule1Copy));

        // sort by invalid or default criteria
        Comparator<Person> invalidAscComparator = PersonComparator.getComparator(SortCriteria.INVALID, SortOrder.ASC);
        // ascending
        assertTrue(invalidAscComparator.compare(alice, bob) < 0);
        assertEquals(0, invalidAscComparator.compare(alice, aliceCopy));
        // descending
        assertTrue(invalidAscComparator.compare(alice, bob) < 0);
        assertEquals(0, invalidAscComparator.compare(alice, aliceCopy));
    }
}
