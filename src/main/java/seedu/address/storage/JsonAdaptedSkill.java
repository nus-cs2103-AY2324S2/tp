package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.skill.Skill;

/**
 * Jackson-friendly version of {@link Skill}.
 */
class JsonAdaptedSkill {

    private final String skillName;
    private boolean important;

    /**
     * Constructs a {@code JsonAdaptedSkill} with the given {@code skillName}
     * and {@code important}.
     */
    @JsonCreator
    public JsonAdaptedSkill(@JsonProperty("name") String skillName,
                            @JsonProperty("important") boolean important) {
        this.skillName = skillName;
        this.important = important;
    }

    /**
     * Converts a given {@code Skill} into this class for Jackson use.
     */
    public JsonAdaptedSkill(Skill source) {
        skillName = source.skillName;
        important = source.getImportant();
    }

    /**
     * Converts this Jackson-friendly adapted skill object into the model's {@code Skill} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted skill.
     */
    public Skill toModelType() throws IllegalValueException {
        return new Skill(skillName, important);
    }

}
