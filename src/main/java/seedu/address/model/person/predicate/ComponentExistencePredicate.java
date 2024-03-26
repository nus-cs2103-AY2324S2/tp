package seedu.address.model.person.predicate;

import seedu.address.model.person.Person;

/**
 * A component that filters based on the existence of a nullable component of {@link Person}
 */
public abstract class ComponentExistencePredicate implements ComponentPredicate {
    private final Component component;

    /**
     * The type of nullable components in {@link Person}
     */
    public enum Component {
        Tags
    }

    public ComponentExistencePredicate(Component component) {
        this.component = component;
    }

    protected boolean exists(Person person) {
        switch (component) {
        case Tags:
            return !person.getTags().isEmpty();
        default:
            throw new IllegalStateException("Unexpected value: " + component);
        }
    }

    /**
     * A predicate that checks whether the component is non-empty.
     */
    public static class Some extends ComponentExistencePredicate {
        public Some(Component component) {
            super(component);
        }

        @Override
        public boolean test(Person person) {
            return exists(person);
        }
    }

    /**
     * A predicate that checks whether the component is empty
     */
    public static class None extends ComponentExistencePredicate {
        public None(Component component) {
            super(component);
        }

        @Override
        public boolean test(Person person) {
            return !exists(person);
        }
    }
}
