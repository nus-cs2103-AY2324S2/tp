package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.patient.Patient;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Case Log} using a {@code PersonCard}.
 */
class CaseLogListViewCell extends ListCell<Patient> {
    @Override
    protected void updateItem(Patient patient, boolean empty) {
        super.updateItem(patient, empty);

        if (empty || patient == null) {
            setGraphic(null);
            setText(null);
        } else {
            setGraphic(new PersonCard(patient, getIndex() + 1).getRoot());
        }
    }
}
