package scrolls.elder.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import scrolls.elder.model.Datastore;
import scrolls.elder.model.LogStore;
import scrolls.elder.model.log.Log;

/**
 * A utility class containing a list of {@code Log} objects to be used in tests.
 * Relies on {@link TypicalPersons} to provide the typical persons.
 */
public class TypicalLogs {
    /**
     * Log without a log ID
     */
    public static final Log LOG_ALICE_TO_ELLE;
    /**
     * Log with a log ID
     */
    public static final Log LOG_BENSON_TO_FIONA;
    private static final Datastore datastore;

    static {
        datastore = new Datastore();
        datastore.getMutablePersonStore().resetData(TypicalPersons.getTypicalPersonStore());

        Calendar calendar = new GregorianCalendar();

        calendar.set(2024, Calendar.MARCH, 7);
        final Date marchSeventh = calendar.getTime();
        LOG_ALICE_TO_ELLE = new Log(datastore, 0, 4, 2, marchSeventh, "Was great!");

        calendar.set(2024, Calendar.APRIL, 8);
        final Date aprilEighth = calendar.getTime();
        Log tempLog = new Log(datastore, 1, 5, 3, aprilEighth, "Was okay.");
        LOG_BENSON_TO_FIONA = new Log(1, tempLog);
    }

    private TypicalLogs() {} // prevents instantiation

    /**
     * Returns an {@code LogStore} with all the typical logs.
     */
    public static LogStore getTypicalLogStore() {
        LogStore store = new LogStore();
        for (Log log : getTypicalLogs()) {
            store.addLog(log);
        }
        return store;
    }

    public static List<Log> getTypicalLogs() {
        return new ArrayList<>(Arrays.asList(LOG_ALICE_TO_ELLE, LOG_BENSON_TO_FIONA));
    }
}
