package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import seedu.address.model.module.ModuleCode;

public class ModuleListPanelTest {

    private ModuleListPanel moduleListPanel;
    private ObservableList<ModuleCode> moduleCodes;

    @BeforeAll
    public static void initToolkit() {
        new JFXPanel(); // Initializes the JavaFX Toolkit
    }
    @BeforeEach
    public void setUp() {
        moduleCodes = FXCollections.observableArrayList();
        moduleListPanel = new ModuleListPanel(moduleCodes);
    }

    @Test
    public void constructor_withListOfModuleCodes_initializesListView() {
        assertNotNull(moduleListPanel.moduleListView);
    }

    @Test
    public void constructor_withListOfModuleCodes_setsCellFactory() {
        assertNotNull(moduleListPanel.moduleListView.getCellFactory());
    }

    @Test
    public void constructor_withListOfModuleCodes_displaysCorrectModuleCards() {
        moduleCodes.add(new ModuleCode("CS2103"));
        assertEquals(1, moduleListPanel.moduleListView.getItems().size());
        // Add more assertions as needed
    }

    // Add more test cases as needed
}
