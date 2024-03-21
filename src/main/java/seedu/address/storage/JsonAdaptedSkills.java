package seedu.address.storage;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Skills;

/**
 * Jackson-friendly version of {@link Skills}.
 */
public class JsonAdaptedSkills {
    private Set<String> skills = new HashSet<>();

    public JsonAdaptedSkills() {
        this.skills = new HashSet<>();
    }

    /**
     * Constructs a {@code JsonAdaptedSkills} with the given {@code skills}.
     */
    public JsonAdaptedSkills(Skills skills) {
        this.skills = new HashSet<>(skills.getSkills());
    }

    public JsonAdaptedSkills(Set<String> skills) {
        this.skills = new HashSet<>(skills);
    }

    public Set<String> getSkills() {
        return new HashSet<>(skills);
    }

    public void setSkills(Set<String> skills) {
        this.skills = new HashSet<>(skills);
    }

    public void addSkills(Set<String> skills) {
        this.skills.addAll(skills);
    }

    /**
     * Converts this Jackson-friendly adapted skills object into the model's {@code Skills} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skills.
     */
    public Skills toModelType() throws IllegalValueException {
        for (String skill : skills) {
            if (!Skills.isValidSkills(skill)) {
                throw new IllegalValueException(Skills.MESSAGE_CONSTRAINTS);
            }
        }
        return new Skills(skills);
    }
}
