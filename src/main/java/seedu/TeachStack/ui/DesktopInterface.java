package seedu.TeachStack.ui;

import java.io.IOException;
import java.net.URI;

/**
 * Interface for desktop actions such as sending emails.
 */
public interface DesktopInterface {

    /**
     * Opens the default mail client with the specified email URI.
     *
     * @param mailtoUrl The URI representing the email address.
     * @throws IOException If an I/O error occurs while launching the mail client.
     */

    void mail(URI mailtoUrl) throws IOException;

    /**
     * Enum representing desktop actions.
     */
    enum Action {
        MAIL
    }
}

