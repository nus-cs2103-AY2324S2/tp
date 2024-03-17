package seedu.address.model.asset;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssetTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Asset.of(null));
    }

    @Test
    public void constructor_invalidAssetName_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Asset.of("")); // empty string
        assertThrows(IllegalArgumentException.class, () -> Asset.of("Hello@World")); // not alphanumeric
        assertThrows(IllegalArgumentException.class, () -> Asset.of("Testing 123")); // contains space
        assertThrows(IllegalArgumentException.class, () -> Asset.of("Café123")); // contains illegal unicode character
    }

    @Test
    public void isValidAssetName() {
        assertDoesNotThrow(() -> Asset.of("a"));
        assertDoesNotThrow(() -> Asset.of("abc"));
        assertDoesNotThrow(() -> Asset.of("validAsset"));
        assertDoesNotThrow(() -> Asset.of("ValidAsset"));
        assertDoesNotThrow(() -> Asset.of("VALIDAsset"));
    }

}
