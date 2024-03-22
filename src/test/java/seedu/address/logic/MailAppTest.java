package seedu.address.logic;

import org.junit.jupiter.api.Test;

import org.mockito.MockedStatic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static seedu.address.testutil.TypicalContacts.GEORGE;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.awt.Desktop;


public class MailAppTest {

    @Test
    public void handleEmailClicked_validEmail_opensMailApp() {
        MailApp mailApp = new MailApp(GEORGE);
        mailApp.handleEmailClicked();
    }

    @Test
    public void handleEmailClicked_noDesktopMailApp_throwsException() {
        // Create a mock object for the Desktop class
        try (MockedStatic desktopMock = mockStatic(Desktop.class)) {
            desktopMock.when(Desktop::isDesktopSupported).thenReturn(false);

            MailApp mailApp = new MailApp(GEORGE);
            assertThrows(RuntimeException.class, () -> mailApp.handleEmailClicked());
        }
    }

    @Test
    public void handleEmailClicked_noMailActionSupported_throwsException() {
        try (MockedStatic<Desktop> desktopMock = mockStatic(Desktop.class)) {
            Desktop desktop = mock(Desktop.class);
            when(Desktop.getDesktop()).thenReturn(desktop);
            when(desktop.isSupported(Desktop.Action.MAIL)).thenReturn(false);

            MailApp mailApp = new MailApp(GEORGE);

            assertThrows(RuntimeException.class, () -> mailApp.handleEmailClicked());
        }
    }
}
