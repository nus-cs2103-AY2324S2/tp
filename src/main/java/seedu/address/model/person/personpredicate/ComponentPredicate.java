package seedu.address.model.person.personpredicate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.person.Person;

/**
 * A predicate to filter a specific component of a Person class.
 * <p>
 *     The predicate matches when any condition is true. In the case of singular values,
 *     this means that the predicate has to match. However, with aggregate values such as tags, if
 *     only one value matches that counts as a success.
 * </p>
 * <p>
 *     The matching is case insensitive.
 * </p>
 */
public abstract class ComponentPredicate implements Predicate<Person> {
    private final String input;
    private final Component component;

    /**
     * The available components in a {@link Person} class.
     */
    public enum Component {
        Name,
        Address,
        Email,
        Tags,
        Phone,
    }

    /**
     * Constructs a component predicate.
     * @param input The input to match with.
     * @param component The component to match on.
     */
    public ComponentPredicate(String input, Component component) {
        requireAllNonNull(input, component);

        this.input = input.toLowerCase();
        this.component = component;
    }

    protected String getInput() {
        return input;
    }

    protected Pattern makeWordsPattern() {
        String alternatives = Arrays.stream(input.split(" "))
                .map(Pattern::quote)
                .collect(Collectors.joining("|"));
        return Pattern.compile(String.format("\\<(%s)\\>", alternatives));
    }

    /**
     * Extracts the required component's values from the person.
     * @return All matchable values in the component.
     */
    // TODO: Remove the indirection and create a Component abstract class for component values.
    // Determer is crying from looking at this code ngl.
    protected Stream<String> extract(Person person) {
        Stream<String> stream;
        switch(component) {
        case Name:
            stream = Stream.of(person.getName().fullName);
            break;
        case Email:
            stream = Stream.of(person.getEmail().value);
            break;
        case Phone:
            stream = Stream.of(person.getPhone().value);
            break;
        case Tags:
            stream = person.getTags().stream().map(tag -> tag.tagName);
            break;
        case Address:
            stream = Stream.of(person.getAddress().value);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + component);
        }
        return stream.map(String::toLowerCase);
    }

    /**
     * A predicate that checks whether the component in Person is exactly equal to given input.
     */
    public abstract static class Is extends ComponentPredicate {
        public Is(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> str.equals(input));
        }
    }

    /**
     * A predicate that checks whether the component in Person is not equal to the given input.
     */
    public abstract static class Isnt extends ComponentPredicate {
        public Isnt(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> !str.equals(input));
        }
    }

    /**
     * A predicate that checks whether the component contains the given input.
     * This is basically the same as a substring match.
     */
    public abstract static class Has extends ComponentPredicate {
        public Has(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> str.contains(input));
        }
    }


    /**
     * A predicate that checks whether the component doesn't contain the given input.
     */
    public abstract static class Hasnt extends ComponentPredicate {
        public Hasnt(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> !str.contains(input));
        }
    }

    /**
     * A predicate that checks whether the component starts with the given input.
     */
    public abstract static class StartsWith extends ComponentPredicate {
        public StartsWith(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> str.startsWith(input));
        }
    }

    /**
     * A predicate that checks whether the component ends with the given input.
     */
    public abstract static class EndsWith extends ComponentPredicate {
        public EndsWith(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            String input = getInput();
            return extract(person).anyMatch(str -> str.endsWith(input));
        }
    }

    /**
     * A predicate that checks whether the component is non-empty.
     */
    public abstract static class Some extends ComponentPredicate {
        public Some(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            return extract(person).anyMatch(str -> !str.isBlank());
        }
    }

    /**
     * A predicate that checks whether the component is empty.
     */
    public abstract static class None extends ComponentPredicate {
        public None(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            return extract(person).anyMatch(String::isBlank);
        }
    }

    /**
     * A predicate that checks whether the given component contains any of the given words.
     * This predicate splits its input into different words by whitespace and checks all the words individually.
     */
    public abstract static class Word extends ComponentPredicate {
        public Word(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            var matcher = makeWordsPattern().asPredicate();
            return extract(person).anyMatch(matcher);
        }
    }

    /**
     * A predicate that checks whether the given component doesn't contain any of the given words.
     * This predicate splits its input into different words by whitespace and checks all the words individually.
     */
    public abstract static class NoWord extends ComponentPredicate {
        public NoWord(String input, Component component) {
            super(input, component);
        }

        @Override
        public boolean test(Person person) {
            var matcher = makeWordsPattern().asPredicate();
            return extract(person).anyMatch(str -> !matcher.test(str));
        }
    }
}
