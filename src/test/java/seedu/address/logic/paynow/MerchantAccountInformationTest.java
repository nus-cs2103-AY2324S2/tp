package seedu.address.logic.paynow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MerchantAccountInformationTest {
    @Test
    public void constructor_success() {
        new MerchantAccountInformation("81472392");
    }

    @Test
    public void toStringMethod() {
        assertEquals(
                new MerchantAccountInformation("81472392").toString(),
                "0009SG.PAYNOW"
                        + "01010"
                        + "0211+6581472392"
                        + "03011"
        );
    }
}
