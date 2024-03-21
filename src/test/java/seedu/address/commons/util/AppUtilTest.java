package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class AppUtilTest {

    @Test
    public void getImage_exitingImage() {
        assertNotNull(AppUtil.getImage("/images/address_book_32.png"));
    }

    @Test
    public void getImage_nullGiven_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> AppUtil.getImage(null));
    }

    @Test
    public void checkArgument_true_nothingHappens() {
        AppUtil.checkArgument(true);
        AppUtil.checkArgument(true, "");
    }

    @Test
    public void checkArgument_falseWithoutErrorMessage_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> AppUtil.checkArgument(false));
    }

    @Test
    public void checkArgument_falseWithErrorMessage_throwsIllegalArgumentException() {
        String errorMessage = "error message";
        assertThrows(IllegalArgumentException.class, errorMessage, () -> AppUtil.checkArgument(false, errorMessage));
    }

    @Test
    @EnabledOnOs({ OS.WINDOWS })
    public void isWindows() {
        assertTrue(AppUtil.OS.isWindows());
    }

    @Test
    @EnabledOnOs({ OS.MAC })
    public void isMac() {
        assertTrue(AppUtil.OS.isMac());
    }

    @Test
    @EnabledOnOs({ OS.LINUX })
    public void isLinux() {
        assertTrue(AppUtil.OS.isLinux());
    }
}
