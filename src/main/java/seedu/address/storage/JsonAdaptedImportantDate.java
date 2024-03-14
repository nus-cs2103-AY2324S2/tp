package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.patient.ImportantDate;


/**
 * Jackson-friendly version of {@link ImportantDate}.
 */
public class JsonAdaptedImportantDate {
    private final String name;
    private final String dateTime;

    /**
     * Constructs a {@code JsonAdaptedImportantDate} with the given {@code eventName}, {@code dateTime}
     */
    @JsonCreator
    public JsonAdaptedImportantDate(@JsonProperty("name") String name,
                                    @JsonProperty("dateTime") String dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    /**
     * Converts a given {@code ImportantDate} into this class for Jackson use.
     */
    public JsonAdaptedImportantDate(ImportantDate source) {
        this.name = source.name;
        this.dateTime = convertToExpectedDateTimeFormat(source.importantDate, source.startTime, source.endTime);
    }

    public String getName() {
        return this.name;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code ImportantDate} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted ImportantDate.
     */
    public ImportantDate toModelType() throws IllegalValueException {
        if (!ImportantDate.isValidImportantDate(this.dateTime)) {
            throw new IllegalValueException(ImportantDate.MESSAGE_CONSTRAINTS);
        }
        return new ImportantDate(this.name, this.dateTime);
    }

    private String convertToExpectedDateTimeFormat(String importantDate, String startTime, String endTime) {
        if (startTime == null) {
            return importantDate;
        }

        return String.format("%s, %s - %s", importantDate, startTime, endTime);
    }
}
