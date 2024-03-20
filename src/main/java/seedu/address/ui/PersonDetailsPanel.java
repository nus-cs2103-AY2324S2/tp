package seedu.address.ui;

import javafx.scene.layout.Region;

/**
 * A UI component that displays the details of a {@code Person}.
 */
public class PersonDetailsPanel extends UiPart<Region> {

    public static final String FXML = "PersonDetailsPanel.fxml";
    public PersonDetailsPanel() {
        super(FXML);
    }
}
