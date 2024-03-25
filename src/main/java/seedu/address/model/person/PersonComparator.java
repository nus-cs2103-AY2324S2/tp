package seedu.address.model.person;

import java.util.Comparator;

/**
 * Comparator for comparing persons.
 */
public class PersonComparator {
    /**
     * Returns a comparator based on the sort criteria and sort order.
     *
     * @param sortCriteria the sort criteria
     * @param sortOrder the sort order
     * @return the comparator
     */
    public static Comparator<Person> getComparator(SortCriteria sortCriteria, SortOrder sortOrder) {
        Comparator<Person> comparator;
        switch (sortCriteria) {
        case NAME:
            comparator = Comparator.comparing(Person::getName);
            break;
        case PHONE:
            comparator = Comparator.comparing(Person::getPhone);
            break;
        case EMAIL:
            comparator = Comparator.comparing(Person::getEmail);
            break;
        case ADDRESS:
            comparator = Comparator.comparing(Person::getAddress);
            break;
        case PRIORITY:
            comparator = Comparator.comparing(Person::getPriority);
            break;
        case BIRTHDAY:
            comparator = Comparator.comparing(Person::getBirthday);
            break;
        case LASTMET:
            comparator = Comparator.comparing(Person::getLastMet);
            break;
        case SCHEDULE:
            comparator = Comparator.comparing(Person::getSchedule);
            break;
        default:
            return Comparator.comparing(Person::getName);
        }
        if (sortOrder == SortOrder.DESC) {
            return comparator.reversed();
        }
        return comparator;
    }
}
