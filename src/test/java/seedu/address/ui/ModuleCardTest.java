package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;
import seedu.address.model.module.ModuleCode;

public class ModuleCardTest {

    private ModuleCode moduleCode;
    private ModuleCard moduleCard;

    @BeforeAll
    public static void initJavaFX() {
        // Initialize JavaFX toolkit
        new JFXPanel();
    }

    @BeforeEach
    public void setUp() {
        moduleCode = new ModuleCode("CS2103");
        moduleCard = new ModuleCard(moduleCode);
    }

    @Test
    public void constructor_validModuleCode_displayCorrectModuleCode() {
        assertEquals("CS2103", moduleCard.moduleCodeLabel.getText());
    }

    @Test
    public void constructor_validModuleCode_displayCorrectTutorialClass() {
        assertEquals("[]", moduleCard.tutorialClassLabel.getText());
    }
}

