package seedu.address.logic.paynow;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;

public class PayNowCodeTest {
    @Test
    public void generatePayNowQrCode_success() throws IOException, WriterException {
        PayNowCode.generatePayNowQrCode("82371234", 12.3F);
    }
}
