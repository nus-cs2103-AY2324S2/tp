package seedu.address.model.person;

import java.util.function.Predicate;

public class hasLastContactedPredicate implements Predicate<Person> {

    @Override
    public boolean test(Person person) {
        return person.getLastcontact().isLastContacted();
    }
}
