package seedu.address.commons.util;

import javafx.scene.control.TextInputControl;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;

import java.util.function.Consumer;

/**
 * This class contains JavaFX-specific tasks
 */
public class UiUtil {

    /**
     * Creates a keyboard shortcut
     * @param region Obtained by calling getRoot() inside JavaFX code
     * @param keyCode The specific key code you want to use as a shortcut
     * @param func The function to execute upon triggering
     */
    public static void setShortcut(Region region, KeyCode keyCode, Consumer<KeyEvent> func) {
        region.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == keyCode) {
                event.consume(); // This stops the event from doing what it would otherwise do without this function
                func.accept(event);
            }
        });
    }

    /**
     * Sets the text of an input. This abstracts away the logic of setting the position caret manually
     * @param textInput Text input to modify
     * @param text Text to set
     */
    public static void setText(TextInputControl textInput, String text) {
        textInput.setText(text);
        textInput.positionCaret(text.length());
    }

}
