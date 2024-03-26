package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;


/**
 * A ui for the patient note that is displayed.
 */
public class NoteDisplay extends UiPart<Region> {

    private static final String FXML = "NoteDisplay.fxml";

    @FXML
    private TextArea noteDisplay;

    public NoteDisplay() {
        super(FXML);
    }

    public void setNoteToUser(String noteToUser) {
        noteDisplay.setText(noteToUser);
    }
}
