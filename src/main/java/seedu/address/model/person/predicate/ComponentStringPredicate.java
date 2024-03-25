package seedu.address.model.person.predicate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.person.Person;

/**
 * A predicate to filter a specific component of a Person class that can be treated like a string.
 * The matching is case-insensitive.
 */
public abstract class ComponentStringPredicate implements ComponentPredicate {
    private final String input;
    private final Component component;

    /**
     * The available components in a {@link Person} class that can be searched through like a string.
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
     *
     * @param input     The input to match with.
     * @param component The component to match on.
     */
    public ComponentStringPredicate(String input, Component component) {
        requireAllNonNull(input, component);
        assert !input.trim().isEmpty() : "Input should not be empty";

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
        return Pattern.compile(String.format("\\b(%s)\\b", alternatives));
    }

    /**
     * Extracts the required component's values from the person.
     *
     * @return All matchable values in the component.
     */
    // TODO: Remove the indirection and create a Component abstract class for component values.
    // Determer is crying from looking at this code ngl.
    protected Stream<String> extract(Person person) {
        Stream<String> stream;
        switch (component) {
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
    public static class Is extends ComponentStringPredicate {
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
    public static class Isnt extends ComponentStringPredicate {
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
    public static class Has extends ComponentStringPredicate {
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
    public static class Hasnt extends ComponentStringPredicate {
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
    public static class StartsWith extends ComponentStringPredicate {
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
    public static class EndsWith extends ComponentStringPredicate {
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
     * A predicate that checks whether the given component contains any of the given words.
     * This predicate splits its input into different words by whitespace and checks all the words individually.
     */
    public static class Word extends ComponentStringPredicate {
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
    public static class NoWord extends ComponentStringPredicate {
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
