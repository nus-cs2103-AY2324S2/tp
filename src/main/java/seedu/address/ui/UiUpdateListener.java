package seedu.address.ui;

import seedu.address.model.person.Classes;

/**
 * Monitors for changes requiring UI to be updated
 */
public interface UiUpdateListener {

    /**
     * Updates Ui when a new class is selected.
     */
    void updateUiOnClassSelected(Classes selectedClass);
}
