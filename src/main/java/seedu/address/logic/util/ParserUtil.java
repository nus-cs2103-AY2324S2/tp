package seedu.address.logic.util;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.asset.Asset;

/**
 * Contains utility methods used for parsing strings.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_ASSET = "Asset cannot be empty.";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalArgumentException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws IllegalArgumentException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new IllegalArgumentException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code assetToEdit} into an {@code Asset} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws IllegalArgumentException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Asset parseAsset(String assetToEdit) throws IllegalArgumentException {
        String trimmedAssetToEdit = assetToEdit.trim();
        if (trimmedAssetToEdit.isEmpty()) {
            throw new IllegalArgumentException(MESSAGE_INVALID_ASSET);
        }
        return Asset.of(trimmedAssetToEdit);
    }
}
