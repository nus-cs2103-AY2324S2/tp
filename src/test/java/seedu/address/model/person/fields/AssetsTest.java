package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.asset.Asset;

class AssetsTest {

    private static final String INVALID_ASSET = "#nut";
    private static final String VALID_ASSET_1 = "screwdriver";
    private static final String VALID_ASSET_2 = "hammer";

    private static final String[] emptyStringArray = new String[0];

    private static final Asset[] emptyAssetArray = new Asset[0];

    @Test
    public void constructor_emptyArray_success() {
        assertEquals(new Assets(emptyAssetArray), new Assets(emptyStringArray));
    }

    @Test
    public void parseAssets_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Assets.of(null));
    }

    @Test
    public void parseAssets_collectionWithInvalidAssets_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Assets.of(Arrays.asList(VALID_ASSET_1, INVALID_ASSET)));
    }

    @Test
    public void parseAssets_emptyCollection_returnsEmptyAssets() throws Exception {
        assertEquals(new Assets(new Asset[0]), Assets.of(Collections.emptyList()));
    }

    @Test
    public void parseAssets_collectionWithValidAssets_returnsAssetSet() throws Exception {
        Assets actualAssets = Assets.of(Arrays.asList(VALID_ASSET_1, VALID_ASSET_2));
        Assets expectedAssets = new Assets(Asset.of(VALID_ASSET_1), Asset.of(VALID_ASSET_2));

        assertEquals(expectedAssets, actualAssets);
    }

    @Test
    void equals_null_false() {
        assertNotEquals(null, new Assets(emptyAssetArray));
    }
}
