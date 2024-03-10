package seedu.address.commons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * A utility class to handle date parsing and formatting.
 */
public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

    /**
     * Parses a date string into a Date object.
     *
     * @param date The date string to parse.
     * @return The parsed Date object.
     * @throws IllegalValueException If the date string is not in the correct format.
     */
    public static Date parse(String date) throws IllegalValueException {
        try {
            return DATE_FORMATTER.parse(date);
        } catch (ParseException e) {
            throw new IllegalValueException(DATE_FORMAT + " is the only supported date format.");
        }
    }

    /**
     * Formats a Date object into a date string.
     *
     * @param date The Date object to format.
     * @return The formatted date string.
     */
    public static String format(Date date) {
        return DATE_FORMATTER.format(date);
    }

}
