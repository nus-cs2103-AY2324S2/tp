package educonnect.testutil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import educonnect.model.student.timetable.Period;
import educonnect.model.student.timetable.Timetable;
import educonnect.model.student.timetable.exceptions.OverlapPeriodException;

/**
 * A utility class containing a list of {@code String, Period, Timetable}
 * and other peripheral objects to be used in tests.
 */
public class TypicalTimetableAndValues {
    // additional strings
    public static final String PERIOD_SPACER = ", ";
    public static final String WHITESPACE = " \t\r\n";
    public static final String EMPTY = "";

    // invalid period strings used in parsing
    public static final String INVALID_PERIOD1 = "25-30";
    public static final String INVALID_PERIOD2 = "a14-16";

    // valid period strings used in parsing
    public static final String VALID_PERIOD_NAME = "period";
    public static final String VALID_PERIOD1 = "13-15";
    public static final String VALID_PERIOD2 = "0-23";
    public static final String VALID_PERIOD3 = "16-18";
    public static final String VALID_PERIODS_STRING1 =
            VALID_PERIOD1 + PERIOD_SPACER + VALID_PERIOD3;
    public static final String VALID_PERIODS_STRING2 =
            VALID_PERIOD3 + PERIOD_SPACER + VALID_PERIOD1;
    public static final String FULL_STRING_WITH_INVALID_INPUT =
            INVALID_PERIOD1 + PERIOD_SPACER + INVALID_PERIOD2 + PERIOD_SPACER + VALID_PERIOD1;
    public static final String FULL_STRING_VALID_INPUT = VALID_PERIOD1 + PERIOD_SPACER + VALID_PERIOD3;
    public static final String FULL_STRING_WITH_WHITESPACE_VALID_INPUT =
            WHITESPACE + VALID_PERIOD1 + WHITESPACE + PERIOD_SPACER + WHITESPACE + VALID_PERIOD3 + WHITESPACE;

    // valid period values used in tests for assertions
    public static final int VALID_PERIOD1_VALUE1 = 13;
    public static final int VALID_PERIOD1_VALUE2 = 15;
    public static final int VALID_PERIOD2_VALUE1 = 0;
    public static final int VALID_PERIOD2_VALUE2 = 23;
    public static final int VALID_PERIOD3_VALUE1 = 16;
    public static final int VALID_PERIOD3_VALUE2 = 18;

    // add command arguments for Timetable
    public static final String VALID_ADD_COMMAND_TIMETABLE_ARGUMENTS_1 = " mon: 13-15, 16-18 thu: 16-18, 13-15";
    public static final String VALID_ADD_COMMAND_TIMETABLE_ARGUMENTS_2 = " tue: 16-18, 13-15 wed: 13-15, 16-18";

    // valid period objects used in assertions.
    public static final Period EXPECTED_PERIOD_1 = new Period(VALID_PERIOD_NAME,
            LocalTime.of(VALID_PERIOD1_VALUE1, 0, 0),
            LocalTime.of(VALID_PERIOD1_VALUE2, 0, 0));
    public static final Period EXPECTED_PERIOD_2 = new Period(VALID_PERIOD_NAME,
            LocalTime.of(VALID_PERIOD2_VALUE1, 0, 0),
            LocalTime.of(VALID_PERIOD2_VALUE2, 0, 0));
    public static final Period EXPECTED_PERIOD_3 = new Period(VALID_PERIOD_NAME,
            LocalTime.of(VALID_PERIOD3_VALUE1, 0, 0),
            LocalTime.of(VALID_PERIOD3_VALUE2, 0, 0));

    // Optional ArrayList of Period objects, used in assertions.
    public static final Optional<ArrayList<Period>> VALID_PERIOD_OPTIONAL_ARRAYLIST =
            Optional.of(new ArrayList<>(List.of(EXPECTED_PERIOD_1, EXPECTED_PERIOD_3)));
    public static final Optional<ArrayList<Period>> INVALID_PERIOD_OPTIONAL_ARRAYLIST =
            Optional.of(new ArrayList<>(List.of(EXPECTED_PERIOD_1, EXPECTED_PERIOD_2)));

    // ArrayLists of valid Periods
    public static final ArrayList<Period> VALID_PERIODS_LIST1 =
            buildPeriodList(new Period[] {EXPECTED_PERIOD_1, EXPECTED_PERIOD_3});
    public static final ArrayList<Period> VALID_PERIODS_LIST2 =
            buildPeriodList(new Period[] {EXPECTED_PERIOD_3, EXPECTED_PERIOD_1});

    // valid List of Period Strings for all days
    public static final ArrayList<String> VALID_TIMETABLE_INPUT_1 = new ArrayList<>(
            getListOfPeriods(List.of(VALID_PERIODS_STRING1, EMPTY, EMPTY, VALID_PERIODS_STRING2, EMPTY)));
    public static final ArrayList<String> VALID_TIMETABLE_INPUT_2 = new ArrayList<>(
            getListOfPeriods(List.of(EMPTY, VALID_PERIODS_STRING2, VALID_PERIODS_STRING1, EMPTY, EMPTY)));

    // valid Timetable
    public static final Timetable VALID_TIMETABLE_1;
    public static final Timetable VALID_TIMETABLE_2;

    public static final Timetable DEFAULT_EMPTY_TIMETABLE = new Timetable();

    static {
        try {
            VALID_TIMETABLE_1 = buildTimetable(new int[] {1, 4},
                    new ArrayList<>(List.of(VALID_PERIODS_LIST1, VALID_PERIODS_LIST2)));
        } catch (OverlapPeriodException e) {
            throw new RuntimeException(e);
        }
    }
    static {
        try {
            VALID_TIMETABLE_2 = buildTimetable(new int[] {2, 3},
                    new ArrayList<>(List.of(VALID_PERIODS_LIST2, VALID_PERIODS_LIST1)));
        } catch (OverlapPeriodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Helper method to create List of Periods
     *
     * @param periods an array pf {@code Period} objects.
     * @return an {@code ArrayList} of {@code Period} objects.
     */
    public static ArrayList<Period> buildPeriodList(Period[] periods) {
        return new ArrayList<>(Arrays.asList(periods));
    }

    /**
     * Helper method to get List of Period Strings, according to number of days in Timetable
     *
     * @param original List of Strings for 5 days in the week.
     * @return List of Strings for 7 days of the week, if {@code Timetable.getTimetable7Days()} is {@code true}.
     */
    public static List<String> getListOfPeriods(List<String> original) {
        ArrayList<String> newList = new ArrayList<>(original);
        if (Timetable.is7Days()) {
            newList.addAll(List.of(EMPTY, EMPTY));
        }
        return newList;
    }

    /**
     * Helper method to build timetable.
     *
     * @param indexesOfDays indexes containing days which has {@code Period} to add.
     * @param periodsEachDay {@code ArrayList} of {@code ArrayList<Period>} objects corresponding to the indexes.
     * @return a {@code Timetable} object.
     * @throws OverlapPeriodException if there is an overlap in the periods given.
     */
    public static Timetable buildTimetable(int[] indexesOfDays, ArrayList<ArrayList<Period>> periodsEachDay)
            throws OverlapPeriodException {
        Timetable timetable = new Timetable();

        for (int i = 0; i < indexesOfDays.length; i++) {
            timetable.addPeriodsToDay(indexesOfDays[i], periodsEachDay.get(i));
        }
        return timetable;
    }
}
