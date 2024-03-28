package seedu.address.model.person.fields;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.asset.Asset;

class AssetsTest {

    private static final String INVALID_ASSET = " ";
    private static final String VALID_ASSET_1 = "screwdriver";
    private static final String VALID_ASSET_2 = "hammer";

    private static final String[] emptyStringArray = new String[0];

    private static final Asset[] emptyAssetArray = new Asset[0];

    @Test
    public void constructor_emptyArray_success() {
        Assets emptyAssets = new Assets(emptyStringArray);
        Assets emptyAssets2 = new Assets(emptyAssetArray);
        assertEquals(emptyAssets, emptyAssets2);
        assertEquals(emptyAssets.hashCode(), emptyAssets2.hashCode());
    }

    @Test
    public void of_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Assets.of(null));
    }

    @Test
    public void of_collectionWithInvalidAssets_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () ->
                Assets.of(Arrays.asList(VALID_ASSET_1, INVALID_ASSET)));
    }

    @Test
    public void of_emptyCollection_returnsEmptyAssets() {
        Assets emptyAssets = new Assets(new Asset[0]);
        Assets emptyAssets2 = Assets.of(Collections.emptyList());
        assertEquals(emptyAssets, emptyAssets2);
        assertEquals(emptyAssets.hashCode(), emptyAssets2.hashCode());
    }

    @Test
    public void of_collectionWithValidAssets_returnsAssetSet() {
        Assets actualAssets = Assets.of(Arrays.asList(VALID_ASSET_1, VALID_ASSET_2));
        Assets expectedAssets = new Assets(Asset.of(VALID_ASSET_1), Asset.of(VALID_ASSET_2));
        assertEquals(expectedAssets, actualAssets);
        assertEquals(expectedAssets.hashCode(), actualAssets.hashCode());
    }

    @Test
    void equals_null_false() {
        assertFalse(new Assets(emptyAssetArray).equals(null));
    }

}
