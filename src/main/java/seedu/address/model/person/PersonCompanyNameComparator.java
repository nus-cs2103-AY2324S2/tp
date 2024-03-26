package seedu.address.model.person;

import java.util.Comparator;

public class PersonCompanyNameComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getCompanyName().companyName.compareTo(p2.getCompanyName().companyName);
    }
}
