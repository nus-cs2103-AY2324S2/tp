package seedu.address.logic.paynow;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import com.google.zxing.WriterException;

/**
 * Represents a PayNow code that when encoded into a string and converted into a QR Code,
 * can be scanned using a banking application to automatically fill in a person's
 * mobile number and amount.
 */
public class PayNowCode extends PayNowPayload {
    private static final int PAYLOAD_FORMAT_INDICATOR_ID = 0;
    private static final int POINT_OF_INITIATION_METHOD_ID = 1;
    private static final int MERCHANT_ACCOUNT_INFORMATION_ID = 26;
    private static final int MERCHANT_CATEGORY_CODE_ID = 52;
    private static final int TRANSACTION_CURRENCY_ID = 53;
    private static final int TRANSACTION_AMOUNT_ID = 54;
    private static final int COUNTRY_CODE_ID = 58;
    private static final int MERCHANT_NAME_ID = 59;
    private static final int MERCHANT_CITY_ID = 60;
    private static final int CRC_ID = 63;
    private static final int SG_CURRENCY_CODE = 702;
    private static final String NA_MERCHANT_CATEGORY = "0000";
    private static final PayNowField PAYLOAD_FORMAT_INDICATOR =
            new PayNowField(PAYLOAD_FORMAT_INDICATOR_ID, "01");
    private static final PayNowField POINT_OF_INITIATION_METHOD =
            new PayNowField(POINT_OF_INITIATION_METHOD_ID, "11");
    private static final PayNowField MERCHANT_CATEGORY_CODE =
            new PayNowField(MERCHANT_CATEGORY_CODE_ID, NA_MERCHANT_CATEGORY);
    private static final PayNowField TRANSACTION_CURRENCY =
            new PayNowField(TRANSACTION_CURRENCY_ID, SG_CURRENCY_CODE);
    private static final PayNowField COUNTRY_CODE =
            new PayNowField(COUNTRY_CODE_ID, "SG");
    private static final PayNowField MERCHANT_NAME =
            new PayNowField(MERCHANT_NAME_ID, "NA");
    private static final PayNowField MERCHANT_CITY =
            new PayNowField(MERCHANT_CITY_ID, "Singapore");
    private static final String PLACEHOLDER_CRC = "0000";

    private PayNowCode(PayNowField... fields) {
        super(fields);
    }

    /**
     * Generates a PayNow QR Code that users can scan with their banking apps which will
     * automatically fill in the phone number and amount passed in as parameters.
     */
    public static ByteArrayInputStream generatePayNowQrCode(String phone, float amount)
            throws WriterException, IOException {
        PayNowField[] fields =
                new PayNowField[]{PAYLOAD_FORMAT_INDICATOR,
                                  POINT_OF_INITIATION_METHOD,
                                  new PayNowField(
                                          MERCHANT_ACCOUNT_INFORMATION_ID, new MerchantAccountInformation(phone)),
                                  MERCHANT_CATEGORY_CODE,
                                  TRANSACTION_CURRENCY,
                                  new PayNowField(TRANSACTION_AMOUNT_ID, amount),
                                  COUNTRY_CODE,
                                  MERCHANT_NAME,
                                  MERCHANT_CITY,
                                  new PayNowField(CRC_ID, PLACEHOLDER_CRC)};

        String encodedFields = Stream.of(fields)
                .map(PayNowField::toString)
                .reduce("", (accumulator, encodedField) -> accumulator + encodedField);

        // Remove the placeholder CRC from the string
        encodedFields = encodedFields.substring(0, encodedFields.length() - PLACEHOLDER_CRC.length());
        fields[fields.length - 1] = new PayNowField(CRC_ID, computeCrc(encodedFields));
        return QrGenerator.generateQrCode(new PayNowCode(fields).toString());
    }

    private static String computeCrc(String payload) {
        int crc = 0xFFFF;
        int msb = crc >> 8;
        int lsb = crc & 255;
        for (char character : payload.toCharArray()) {
            int x = character ^ msb;
            x ^= (x >> 4);
            msb = (lsb ^ (x >> 3) ^ (x << 4)) & 255;
            lsb = (x ^ (x << 5)) & 255;
        }
        crc = (msb << 8) + lsb;
        return String.format("%04X", crc);
    }
}
