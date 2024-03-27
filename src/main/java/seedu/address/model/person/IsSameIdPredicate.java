package seedu.address.model.person;

import java.util.function.Predicate;
/**
 * Tests that a {@code Person}'s {@code Id} matches any of the given Id.
 */
public class IsSameIdPredicate implements Predicate<Person> {

    private int testId;

    public IsSameIdPredicate(Id testId) {
        this.testId = Integer.parseInt(testId.id);
    }

    @Override
    public boolean test(Person person) {
        System.out.println(person.getUniqueId().id);
        System.out.println(testId);
        int personId = Integer.parseInt(person.getUniqueId().id);
        return testId == personId;
    }
}
