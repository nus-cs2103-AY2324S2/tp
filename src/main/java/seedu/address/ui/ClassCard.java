package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.classes.ClassList;

/**
 * An UI component that displays information of a {@code ClassList}.
 */
public class ClassCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";

    public final ClassList classList;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCode;
    @FXML
    private Label tutorialClass;

    /**
     * Creates a {@code ClassCard} with the given {@code ClassList}.
     */
    public ClassCard(ClassList classList) {
        super(FXML);
        this.classList = classList;
        moduleCode.setText(classList.getModuleCode().value);
        tutorialClass.setText(classList.getTutorialClass().value);
    }
}
