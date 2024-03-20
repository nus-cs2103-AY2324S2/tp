package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A UI component that displays the details of a {@code Person}.
 */
public class PersonDetailsPanel extends UiPart<Region> {
    public static final String FXML = "PersonDetailsPanel.fxml";

    @FXML
    private Label text;

    public PersonDetailsPanel() {
        super(FXML);
    }

    public void update(String m) {
        this.text.setText(m);
    }
}
