package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ModuleCode;

/**
 * An UI component that displays information of a {@code ModuleCode}.
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
     * Constructs a {@code ModuleCard} with the given {@code ModuleCode}.
     *
     * @param moduleCode The {@code ModuleCode} to be displayed in the card.
     */
    public ModuleCard(ModuleCode moduleCode) {
        super(FXML);
        this.moduleCode = moduleCode;

        if (moduleCode != null) {
            moduleCodeLabel.setText(moduleCode.toString());
            tutorialClassLabel.setText(moduleCode.getTutorialClasses().toString());
        } else {
            // If moduleCode is null, display the homepage or set labels to empty
            // For example, you could set the labels to display a message indicating no module is selected
            moduleCodeLabel.setText("No module selected");
            tutorialClassLabel.setText("");
            // Alternatively, you could hide the entire ModuleCard if moduleCode is null
            // this.getRoot().setVisible(false);
        }
    }
}

