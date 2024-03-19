package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.layout.StackPane;
import seedu.address.model.module.ModuleCode;

public class ModuleCardTest extends ApplicationTest {

    @Test
    public void constructor_validModuleCode_setsModuleCodeAndTutorialClasses() {
        ModuleCode moduleCode = new ModuleCode("CS2103");
        ModuleCard moduleCard = new ModuleCard(moduleCode);

        // Simulate adding the ModuleCard to a JavaFX scene
        StackPane root = new StackPane();
        root.getChildren().add(moduleCard.getRoot());

        // Interact with the JavaFX application on the JavaFX Application Thread
        // Check if the UI components are initialized correctly
        assertEquals(moduleCode, moduleCard.moduleCode);
        assertEquals(moduleCode.value, moduleCard.moduleCodeLabel.getText());
        assertEquals(moduleCode.getTutorialClasses().toString(), moduleCard.tutorialClassLabel.getText());
    }
}
