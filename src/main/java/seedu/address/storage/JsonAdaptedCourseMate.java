package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Email;
import seedu.address.model.coursemate.Name;
import seedu.address.model.coursemate.Phone;
import seedu.address.model.coursemate.Rating;
import seedu.address.model.coursemate.TelegramHandle;
import seedu.address.model.skill.Skill;

/**
 * Jackson-friendly version of {@link CourseMate}.
 */
class JsonAdaptedCourseMate {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "CourseMate's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String telegramHandle;
    private final String rating;
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedCourseMate} with the given courseMate details.
     */
    @JsonCreator
    public JsonAdaptedCourseMate(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                                 @JsonProperty("email") String email,
                                 @JsonProperty("telegramHandle") String telegramHandle,
                                 @JsonProperty("rating") String rating,
                                 @JsonProperty("skills") List<JsonAdaptedSkill> skills) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegramHandle = telegramHandle;
        this.rating = rating;
        if (skills != null) {
            this.skills.addAll(skills);
        }
    }

    /**
     * Converts a given {@code CourseMate} into this class for Jackson use.
     */
    public JsonAdaptedCourseMate(CourseMate source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        if (source.getTelegramHandle() != null) {
            telegramHandle = source.getTelegramHandle().value;
        } else {
            telegramHandle = "";
        }
        rating = source.getRating().value;
        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted courseMate object into the model's {@code CourseMate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted courseMate.
     */
    public CourseMate toModelType() throws IllegalValueException {
        final List<Skill> courseMateSkills = new ArrayList<>();
        for (JsonAdaptedSkill skill : skills) {
            courseMateSkills.add(skill.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (telegramHandle == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    TelegramHandle.class.getSimpleName()));
        }
        if (telegramHandle != "" && !TelegramHandle.isValidTelegramHandle(telegramHandle)) {
            throw new IllegalValueException(TelegramHandle.MESSAGE_CONSTRAINTS);
        }
        TelegramHandle modelTelegramHandle = null;
        if (telegramHandle != "") {
            modelTelegramHandle = new TelegramHandle(telegramHandle);
        }

        if (rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Rating.class.getSimpleName()));
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);

        final Set<Skill> modelSkills = new HashSet<>(courseMateSkills);
        return new CourseMate(modelName, modelPhone, modelEmail, modelTelegramHandle, modelSkills, modelRating);
    }
}
