package seedu.address.ui;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.embed.swing.JFXPanel;
import seedu.address.model.module.ModuleCode;

public class ModuleCardTest {

    @BeforeAll
    public static void initializeJavaFX() {
        // Initialize JavaFX toolkit
        new JFXPanel();
    }

    @Test
    public void constructor_validModuleCode_setsModuleCodeAndTutorialClasses() {
        ModuleCode moduleCode = new ModuleCode("CS2103");
        ModuleCard moduleCard = new ModuleCard(moduleCode);
        assertEquals(moduleCode, moduleCard.moduleCode);
        assertEquals(moduleCode.value, moduleCard.moduleCodeLabel.getText());
        assertEquals(moduleCode.getTutorialClasses().toString(), moduleCard.tutorialClassLabel.getText());
    }
}
