package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Person;
import seedu.address.model.reservation.Pax;
import seedu.address.model.reservation.Reservation;
import seedu.address.model.reservation.RsvDate;
import seedu.address.model.reservation.RsvTime;

/**
 * Jackson-friendly version of {@link Reservation}.
 */
public class JsonAdaptedReservation {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Reservation's %s field is missing!";

    private final JsonAdaptedPerson person;
    private final String date;
    private final String time;
    private final String pax;

    /**
     * Constructs a {@code JsonAdaptedReservation} with the given reservation details.
     */
    @JsonCreator
    public JsonAdaptedReservation(@JsonProperty("person") JsonAdaptedPerson person,
                                  @JsonProperty("date") String date,
                                  @JsonProperty("time") String time,
                                  @JsonProperty("pax") String pax) {
        this.person = person;
        this.date = date;
        this.time = time;
        this.pax = pax;
    }

    /**
     * Converts a given {@code Reservation} into this class for Jackson use.
     */
    public JsonAdaptedReservation(Reservation source) {
        person = new JsonAdaptedPerson(source.getPerson());
        date = source.getDate().toString();
        time = source.getTime().value;
        pax = source.getPax().value;
    }

    /**
     * Converts this Jackson-friendly adapted reservation object into the model's {@code Reservation} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reservation.
     */
    public Reservation toModelType() throws IllegalValueException {

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                     RsvDate.class.getSimpleName()));
        }
        if (!RsvDate.isValidRsvDate(date)) {
            throw new IllegalValueException(RsvDate.MESSAGE_CONSTRAINTS);
        }
        final RsvDate modelDate = new RsvDate(date);

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    RsvTime.class.getSimpleName()));
        }
        if (!RsvTime.isValidRsvTime(time)) {
            throw new IllegalValueException(RsvTime.MESSAGE_CONSTRAINTS);
        }
        final RsvTime modelTime = new RsvTime(time);

        if (pax == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Pax.class.getSimpleName()));
        }
        if (!Pax.isValidPax(pax)) {
            throw new IllegalValueException(Pax.MESSAGE_CONSTRAINTS);
        }
        final Pax modelPax = new Pax(pax);

        // Person's field checking performed by JsonAdaptedPerson#toModelType()
        Person modelPerson = person.toModelType();

        return new Reservation(modelPerson, modelDate, modelTime, modelPax);
    }
}
