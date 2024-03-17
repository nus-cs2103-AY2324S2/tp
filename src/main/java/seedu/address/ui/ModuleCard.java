package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ModuleCode;

/**
 * An UI component that displays information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleCard.fxml";

    public final ModuleCode moduleCode;

    @FXML
    private HBox cardPane;
    @FXML
    private Label moduleCodeLabel;
    @FXML
    private Label tutorialClassLabel;

    /**
     * Creates a {@code ModuleCard} with the given {@code Module}.
     */
    public ModuleCard(ModuleCode moduleCode) {
        super(FXML);
        this.moduleCode = moduleCode;
        moduleCodeLabel.setText(moduleCode.value);
        tutorialClassLabel.setText(moduleCode.getTutorialClasses().toString());
    }
}
