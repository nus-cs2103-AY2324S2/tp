package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.classes.ClassList;

/**
 * An UI component that displays information of a {@code ClassList}.
 */
public class ClassListCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";

    private ClassList classList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label tutorialClass;

    /**
     * Creates a {@code ClassListCard}.
     */
    public ClassListCard() {
        super(FXML);
        this.classList = null; // Initialize classList as null initially
    }

    /**
     * Updates the {@code ClassListCard} with the given {@code ClassList} information.
     */
    public void updateClassList(ClassList classList) {
        if (classList != null) {
            this.classList = classList;
            moduleCode.setText(classList.getModuleCode().value);
            tutorialClass.setText(classList.getTutorialClass().value);
        } else {
            // Handle case where classList is null (optional)
            moduleCode.setText("");
            tutorialClass.setText("");
        }
    }
}