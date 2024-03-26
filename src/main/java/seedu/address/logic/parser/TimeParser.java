package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Parses input arguments and creates a new AppointmentTime object
 */
public class TimeParser {
    /**
     * The ideal format for an AppointmentTime is:
     * dd/MM/yyyy [x]am-[y]pm
     */
    private static final Pattern DAY =
            Pattern.compile("(0[1-9]|[12][0-9]|3[01])\\/(0[1-9]|1[012])\\/(2[0-9]{3})");
    private static final Pattern TODAY = Pattern.compile("(?i)t[o]?d[a]?y");
    private static final Pattern HOUR = Pattern.compile("([1-9]|1[0-2])(?i)[ap]m");
    private static final Pattern HOUR_WINDOW = Pattern.compile(HOUR + "([ ]?-[ ]?)" + HOUR);
    private static final Pattern DATE_APPOINTMENT_TIME = Pattern.compile(DAY + " " + HOUR_WINDOW);
    private static final Pattern TODAY_APPOINTMENT_TIME = Pattern.compile(TODAY + " " + HOUR_WINDOW);
    private static final String MESSAGE_USAGE = "Use dd/MM/yyyy [x]am-[y]pm";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Parses a {@code String appointmentTime} into a {@code AppointmentTime}.
     * The expected format is dd/MM/yyyy [x]am-[y]pm.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public static AppointmentTime parse(String args) throws ParseException {
        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimeParser.MESSAGE_USAGE));
        }

        Matcher matchDate = TimeParser.DATE_APPOINTMENT_TIME.matcher(args);
        Matcher matchToday = TimeParser.TODAY_APPOINTMENT_TIME.matcher(args);

        if (!matchDate.matches() && !matchToday.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TimeParser.MESSAGE_USAGE));
        }

        if (matchToday.matches()) {
            LocalDate today = LocalDate.now();
            String todayString = today.format(DATE_FORMAT);
            return new AppointmentTime(todayString + args.substring(5));
        }

        return new AppointmentTime(args);
    }
}
