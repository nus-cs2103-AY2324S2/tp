package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.coursemate.CourseMate;
import seedu.address.model.coursemate.Name;
import seedu.address.model.group.Group;
import seedu.address.model.group.TelegramChat;
import seedu.address.model.skill.Skill;

/**
 * Jackson-friendly version of {@link Group}.
 */
public class JsonAdaptedGroup {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Group's %s field is missing!";

    private final String name;
    private final List<JsonAdaptedCourseMate> members = new ArrayList<>();
    private final List<JsonAdaptedSkill> skills = new ArrayList<>();
    private final String telegramChat;

    /**
     * Constructs a {@code JsonAdaptedGroup} with the given group details.
     */
    public JsonAdaptedGroup(@JsonProperty("name") String name,
                            @JsonProperty("members") List<JsonAdaptedCourseMate> members,
                            @JsonProperty("skills") List<JsonAdaptedSkill> skills,
                            @JsonProperty("telegramChat") String telegramChat) {
        this.name = name;
        if (members != null) {
            this.members.addAll(members);
        }
        if (skills != null) {
            this.skills.addAll(skills);
        }
        this.telegramChat = telegramChat;
    }

    /**
     * Converts a given {@code Group} into this class for Jackson use.
     */
    public JsonAdaptedGroup(Group source) {
        name = source.getName().fullName;
        members.addAll(source.asUnmodifiableObservableList().stream()
                .map(JsonAdaptedCourseMate::new)
                .collect(Collectors.toList()));
        skills.addAll(source.getSkills().stream()
                .map(JsonAdaptedSkill::new)
                .collect(Collectors.toList()));
        if (source.getTelegramChat() != null) {
            telegramChat = source.getTelegramChat().value;
        } else {
            telegramChat = "";
        }
    }

    /**
     * Converts this Jackson-friendly adapted group object into the model's {@code Group} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted group.
     */
    public Group toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final List<CourseMate> modelMembers = new ArrayList<>();
        for (JsonAdaptedCourseMate courseMate: this.members) {
            modelMembers.add(courseMate.toModelType());
        }

        final List<Skill> modelSkills = new ArrayList<>();
        for (JsonAdaptedSkill skill: this.skills) {
            modelSkills.add(skill.toModelType());
        }

        Name modelName = new Name(name);

        TelegramChat modelTelegramChat;
        if (telegramChat.isEmpty()) {
            modelTelegramChat = null;
        } else {
            if (!TelegramChat.isValidTelegramChat(telegramChat)) {
                throw new IllegalValueException(TelegramChat.MESSAGE_CONSTRAINTS);
            }
            modelTelegramChat = new TelegramChat(telegramChat);
        }

        return new Group(modelName, modelMembers, modelSkills, modelTelegramChat);
    }
}
