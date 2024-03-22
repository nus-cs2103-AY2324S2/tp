package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import javafx.scene.image.Image;
import seedu.address.MainApp;

/**
 * A container for App specific utility functions
 */
public class AppUtil {
    /**
     * Determines the operating system
     */
    public static enum OS {
        WIN,
        MAC,
        LINUX;

        /**
         * Determines if the operating system is Windows
         *
         * @return True if operating system is Windows, False otherwise
         */
        public static boolean isWindows() {
            return System.getProperty("os.name").trim().toLowerCase().contains(WIN.toString().toLowerCase());
        }

        /**
         * Determines if the operating system is MacOS
         *
         * @return True if operating system is MacOS, False otherwise
         */
        public static boolean isMac() {
            return System.getProperty("os.name").trim().toLowerCase().contains(MAC.toString().toLowerCase());
        }

        /**
         * Determines if the operating system is Linux
         *
         * @return True if operating system is Linux, False otherwise
         */
        public static boolean isLinux() {
            return System.getProperty("os.name").trim().toLowerCase().contains(LINUX.toString().toLowerCase());
        }
    }

    /**
     * Gets an {@code Image} from the specified path.
     */
    public static Image getImage(String imagePath) {
        requireNonNull(imagePath);
        return new Image(MainApp.class.getResourceAsStream(imagePath));
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to
     * methods.
     *
     * @throws IllegalArgumentException if {@code condition} is false.
     */
    public static void checkArgument(Boolean condition) {
        if (!condition) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that {@code condition} is true. Used for validating arguments to
     * methods.
     *
     * @throws IllegalArgumentException with {@code errorMessage} if
     *                                  {@code condition} is false.
     */
    public static void checkArgument(Boolean condition, String errorMessage) {
        if (!condition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
