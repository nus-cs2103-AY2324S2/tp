package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interview.Interview;
import seedu.address.model.person.Applicant;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Jackson-friendly version of {@link Interview}.
 */
public class JsonAdaptedInterview {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Interview's %s field is missing!";

    private final String description;
    private final String date;
    private final String startTime;
    private final String endTime;
    private final JsonAdaptedPerson applicant;
    private final JsonAdaptedPerson interviewer;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("description") String description,
                                @JsonProperty("date") String date,
                                @JsonProperty("startTime") String startTime,
                                @JsonProperty("endTime") String endTime,
                                @JsonProperty("applicant") JsonAdaptedPerson applicant,
                                @JsonProperty("interviewer") JsonAdaptedPerson interviewer)
    {
        this.description = description;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.applicant = applicant;
        this.interviewer = interviewer;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        description = source.getDescription();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = source.getDate().format(formatter);
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        applicant = new JsonAdaptedPerson(source.getApplicant());
        interviewer = new JsonAdaptedPerson(source.getInterviewer());


    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview.
     */
    public Interview toModelType() throws IllegalValueException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (this.date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDate.class.getSimpleName()));
        }
        final LocalDate date = LocalDate.parse(this.date, formatter);
        if (this.startTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }
        final LocalTime start = LocalTime.parse(startTime);
        if (this.endTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalTime.class.getSimpleName()));
        }
        final LocalTime end = LocalTime.parse(endTime);
        final Person app = applicant.toModelType();
        final Person inter = interviewer.toModelType();
        return new Interview(app, inter, date, start, end, this.description);

    }
}
