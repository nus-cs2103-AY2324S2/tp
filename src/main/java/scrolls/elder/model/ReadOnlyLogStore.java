package scrolls.elder.model;

import javafx.collections.ObservableList;
import scrolls.elder.model.log.Log;

/**
 * Unmodifiable view of the log store.
 */
public interface ReadOnlyLogStore {
    /**
     * Returns an unmodifiable view of the log list.
     */
    ObservableList<Log> getLogList();
}
