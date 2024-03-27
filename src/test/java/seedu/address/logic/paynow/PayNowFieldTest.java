package seedu.address.logic.paynow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PayNowFieldTest {
    @Test
    public void toStringMethod() {
        assertEquals(new PayNowField(0, "01").toString(), "000201");
        assertEquals(new PayNowField(1, "11").toString(), "010211");
        assertEquals(new PayNowField(2, (float) 1.2).toString(), "02041.20");
        assertEquals(new PayNowField(2, 1.2).toString(), "02041.20");
        assertEquals(new PayNowField(53, "Singapore").toString(), "5309Singapore");
        assertEquals(new PayNowField(62, "").toString(), "6200");
    }
}
