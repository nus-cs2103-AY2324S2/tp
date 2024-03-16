package staffconnect.model.person;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Tag} matches any of the tag names given.
 */
public class PersonHasModulePredicate implements Predicate<Person> {
    private final Module module;

    public PersonHasModulePredicate(Module module) {
        this.module = module;
    }

    @Override
    public boolean test(Person person) {
        if (module == null) {
            return true;
        }
        return person.getModule().value.equalsIgnoreCase(module.value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonHasModulePredicate)) {
            return false;
        }

        PersonHasModulePredicate otherPersonHasTagPredicate = (PersonHasModulePredicate) other;
        return module.equals(otherPersonHasTagPredicate.module);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("module", module).toString();
    }
}
