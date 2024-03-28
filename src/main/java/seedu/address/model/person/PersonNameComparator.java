package seedu.address.model.person;

import java.util.Comparator;

/**
 * Class for comparing two Persons according to the alphabetical order of their full names
 */
public class PersonNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        return firstPerson.getName().compareTo(secondPerson.getName());
    }
}
