package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.exam.Exam;
import seedu.address.model.student.Matric;
import seedu.address.model.student.Reflection;
import seedu.address.model.student.Studio;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    private final Matric matric;
    private final Reflection reflection;
    private final Studio studio;

    private final HashMap<Exam, Score> scores = new HashMap<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Matric matric, Reflection reflection ,
                  Studio studio, Map<Exam, Score> scores) {
        requireAllNonNull(name, phone, email, address, tags, matric, studio);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.matric = matric;
        this.reflection = reflection;
        this.studio = studio;
        this.scores.putAll(scores);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Matric getMatric() {
        return matric;
    }

    public Studio getStudio() {
        return studio;
    }

    public Reflection getReflection() {
        return reflection;
    }

    /**
     * Returns an immutable score set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Map<Exam, Score> getScores() {
        return new HashMap<>(scores);
    }

    /**
     * Returns true if the person has the given exam in the scores.
     */
    public boolean hasExamScore(Exam exam) {
        return scores.containsKey(exam);
    }

    /**
     * Returns a new Person with the given exam added to the scores, maintains immutability.
     */
    public Person removeExam(Exam exam) {
        Map<Exam, Score> newScores = new HashMap<>(scores);
        newScores.remove(exam);
        return new Person(name, phone, email, address, tags, matric, reflection, studio, newScores);
    }

    /**
     * Returns a new Person with the given exam added to the scores, maintains immutability.
     */
    public Person addExamScore(Exam exam, Score score) {
        Map<Exam, Score> newScores = new HashMap<>(scores);
        newScores.put(exam, score);
        return new Person(name, phone, email, address, tags, matric, reflection, studio, newScores);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && matric.equals(otherPerson.matric)
                && reflection.equals(otherPerson.reflection)
                && studio.equals(otherPerson.studio)
                && scores.equals(otherPerson.scores);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, matric, reflection, studio, scores);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("matriculation number", matric)
                .add("reflection", reflection)
                .add("studio", studio)
                .add("scores", scores)
                .toString();
    }
}
