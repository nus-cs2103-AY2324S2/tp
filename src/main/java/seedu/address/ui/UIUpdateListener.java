package seedu.address.ui;

import seedu.address.model.person.Classes;

public interface UIUpdateListener {
    void updateUiOnClassSelected(Classes selectedClass);
}
