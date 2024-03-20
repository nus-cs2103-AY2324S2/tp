package seedu.address.testutil;

import seedu.address.model.asset.Asset;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.*;
import seedu.address.model.util.SampleDataUtil;

import java.util.List;

/**
 * A utility class to help with building Person objects.
 */
public class AssetBuilder {

    public static final String DEFAULT_NAME = "Screwdriver";

    private String name;
    /**
     * Creates a {@code AssetBuilder} with the default details.
     */
    public AssetBuilder() {
        name = DEFAULT_NAME;
    }

    /**
     * Initializes the AssetBuilder with the data of {@code assetToCopy}.
     */
    public AssetBuilder(Asset assetToCopy) {
        name = assetToCopy.get();
    }

    public Asset build() {
        return Asset.of(name);
    }

}
