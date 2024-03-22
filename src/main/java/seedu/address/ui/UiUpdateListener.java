package seedu.address.ui;

import seedu.address.model.person.Classes;

public interface UiUpdateListener {

    /**
     * Updates Ui when a new class is selected.
     */
    void updateUiOnClassSelected(Classes selectedClass);
}
