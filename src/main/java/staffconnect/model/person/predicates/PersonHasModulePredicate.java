package staffconnect.model.person.predicates;

import java.util.function.Predicate;

import staffconnect.commons.util.ToStringBuilder;
import staffconnect.model.person.Module;
import staffconnect.model.person.Person;

/**
 * Tests that a {@code Person}'s {@code Module} matches the given module to filter for.
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
        return person.getModule().toString().equalsIgnoreCase(module.toString());
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

        PersonHasModulePredicate otherPersonHasModulePredicate = (PersonHasModulePredicate) other;
        return module.equals(otherPersonHasModulePredicate.module);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("module", module).toString();
    }
}
