package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    @Test
    public void toStringMethod() {
        GuiSettings guiSettings = new GuiSettings();
        String expected = GuiSettings.class.getCanonicalName() + "{windowWidth=" + guiSettings.getWindowWidth()
                + ", windowHeight=" + guiSettings.getWindowHeight() + ", windowCoordinates="
                + guiSettings.getWindowCoordinates() + "}";
        assertEquals(expected, guiSettings.toString());
    }

    @Test
    void testEquals() {
        GuiSettings guiSettings = new GuiSettings();

        assertFalse(guiSettings.equals(null));
        assertTrue(guiSettings.equals(guiSettings));

        GuiSettings otherGuiSettings = new GuiSettings();
        assertTrue(guiSettings.equals(otherGuiSettings));
        assertEquals(guiSettings.hashCode(), otherGuiSettings.hashCode());
    }

}
