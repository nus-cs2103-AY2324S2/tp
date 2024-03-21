package seedu.address.testutil;

import seedu.address.model.house.Block;
import seedu.address.model.house.Landed;
import seedu.address.model.house.Level;
import seedu.address.model.house.NonLanded;
import seedu.address.model.house.PostalCode;
import seedu.address.model.house.Street;
import seedu.address.model.house.UnitNumber;

/**
 * A utility class containing a list of {@code House} objects to be used in tests.
 */
public class TypicalHouses {

    // Landed house example: This will only be used when house have validation
    public static final Landed HOUSE1 = new Landed(
            new UnitNumber("1"),
            new PostalCode("123456"),
            new Street("Acacia Avenue")
    );

    // Non-landed house example (e.g., HDB Flat)
    public static final NonLanded HOUSE2 = new NonLanded(
            new Block("99B"),
            new Level("10"),
            new PostalCode("654321"),
            new Street("Orchard Road"),
            new UnitNumber("38")
    );

    // Non-landed house example (e.g., Condominium)
    public static final NonLanded HOUSE3 = new NonLanded(
            new Block("99A"),
            new Level("11"),
            new PostalCode("654321"),
            new Street("Toa Payoh Road"),
            new UnitNumber("38")
    );
}
