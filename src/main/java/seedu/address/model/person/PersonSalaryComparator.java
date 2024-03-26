package seedu.address.model.person;

import java.util.Comparator;

public class PersonSalaryComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {

        return Double.compare(p2.getSalary().getSalary1(), p1.getSalary().getSalary1());
    }
}
