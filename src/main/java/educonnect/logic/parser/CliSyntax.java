package educonnect.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TELEGRAM_HANDLE = new Prefix("h/");
    public static final Prefix PREFIX_STUDENT_ID = new Prefix("s/");
    public static final Prefix PREFIX_TIMETABLE = new Prefix("c/");
    public static final Prefix PREFIX_TIMETABLE_MONDAY = new Prefix("mon:");
    public static final Prefix PREFIX_TIMETABLE_TUESDAY = new Prefix("tue:");
    public static final Prefix PREFIX_TIMETABLE_WEDNESDAY = new Prefix("wed:");
    public static final Prefix PREFIX_TIMETABLE_THURSDAY = new Prefix("thu:");
    public static final Prefix PREFIX_TIMETABLE_FRIDAY = new Prefix("fri:");
    public static final Prefix PREFIX_TIMETABLE_SATURDAY = new Prefix("sat:");
    public static final Prefix PREFIX_TIMETABLE_SUNDAY = new Prefix("sun:");
    public static final Prefix[] PREFIXES_TIMETABLE_DAYS = new Prefix[]{
        PREFIX_TIMETABLE_MONDAY, PREFIX_TIMETABLE_TUESDAY, PREFIX_TIMETABLE_WEDNESDAY,
        PREFIX_TIMETABLE_THURSDAY, PREFIX_TIMETABLE_FRIDAY, PREFIX_TIMETABLE_SATURDAY, PREFIX_TIMETABLE_SUNDAY
    };
}
