package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import seedu.address.logic.LogicManager;
import seedu.address.model.ModelManager;
import seedu.address.storage.JsonAddressBookStorage;
import seedu.address.storage.JsonUserPrefsStorage;
import seedu.address.storage.StorageManager;

class UiManagerTest {

    private LogicManager logic;
    private UiManager uiManager;
    private JFXPanel jfxPanel; // Required for JavaFX initialization

    @BeforeEach
    void setUp() {
        jfxPanel = new JFXPanel(); // Required for JavaFX initialization
        ModelManager model = new ModelManager();
        JsonAddressBookStorage addressBookStorage = new JsonAddressBookStorage(Path.of("data", "clinicmate.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(Path.of("preferences.json"));
        StorageManager storage = new StorageManager(addressBookStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
        uiManager = new UiManager(logic);
    }

    @Test
    void start() {
        Platform.runLater(() -> {
            assertDoesNotThrow(() -> uiManager.start(new Stage()));
        });
    }

    @Test
    void showAlertDialogAndWait_throwsExceptionOnNullContent() {
        assertThrows(NullPointerException.class, () -> uiManager.showAlertDialogAndWait(Alert.AlertType.ERROR,
                "title", "header", null));
    }
}
