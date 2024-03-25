package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.startup.Address;
import seedu.address.model.startup.Email;
import seedu.address.model.startup.FundingStage;
import seedu.address.model.startup.Industry;
import seedu.address.model.startup.Name;
import seedu.address.model.startup.Note;
import seedu.address.model.startup.Phone;
import seedu.address.model.startup.Startup;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Startup}.
 */
class JsonAdaptedStartup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Startup's %s field is missing!";

    private final String name;

    private final String industry;

    private final String fundingStage;

    private final String phone;
    private final String email;
    private final String address;

    private final String note;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStartup} with the given startup details.
     */
    @JsonCreator
    public JsonAdaptedStartup(@JsonProperty("name") String name, @JsonProperty("industry") String industry,
                              @JsonProperty("fundingStage") String fundingStage, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("address") String address,
                              @JsonProperty("note") String note,
                              @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.fundingStage = fundingStage;
        this.industry = industry;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.note = note;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Startup} into this class for Jackson use.
     */
    public JsonAdaptedStartup(Startup source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        note = source.getNote().value;
        industry = source.getIndustry().value;
        fundingStage = source.getFundingStage().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted startup object into the model's {@code Startup} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted startup.
     */
    public Startup toModelType() throws IllegalValueException {
        final List<Tag> startupTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            startupTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (industry == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Industry.class.getSimpleName()));
        }
        if (!Industry.isValidIndustry(industry)) {
            throw new IllegalValueException(Industry.MESSAGE_CONSTRAINTS);
        }
        final Industry modelIndustry = new Industry(industry);

        if (fundingStage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                FundingStage.class.getSimpleName()));
        }
        if (!FundingStage.isValidFundingLevel(fundingStage)) {
            throw new IllegalValueException(FundingStage.MESSAGE_CONSTRAINTS);
        }
        final FundingStage modelFundingStage = new FundingStage(fundingStage);

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

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final Note modelNote = new Note(note);

        final Set<Tag> modelTags = new HashSet<>(startupTags);
        return new Startup(modelName, modelFundingStage, modelIndustry,
                modelPhone, modelEmail, modelAddress, modelTags, modelNote);
    }

}
