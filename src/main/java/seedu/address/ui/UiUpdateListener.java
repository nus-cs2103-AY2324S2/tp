package seedu.address.ui;

import seedu.address.model.person.Classes;

public interface UiUpdateListener {
    void updateUiOnClassSelected(Classes selectedClass);
}
