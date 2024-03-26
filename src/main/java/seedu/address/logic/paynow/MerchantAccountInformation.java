package seedu.address.logic.paynow;

/**
 * Represents a Merchant Account Information field within a PayNow QR Code.
 */
public class MerchantAccountInformation extends PayNowPayload {
    private static final String DOMAIN = "SG.PAYNOW";
    private static final int DOMAIN_ID = 0;
    private static final int PROXY_TYPE_ID = 1;
    private static final int MOBILE_NUM_PROXY = 0;
    private static final int MOBILE_NO_ID = 2;
    private static final String SG_COUNTRY_CODE = "+65";
    private static final int EDITABLE_ID = 3;
    private static final int EDITABLE = 1;

    protected MerchantAccountInformation(String phone) {
        super(
                new PayNowField(DOMAIN_ID, DOMAIN),
                new PayNowField(PROXY_TYPE_ID, MOBILE_NUM_PROXY),
                new PayNowField(MOBILE_NO_ID, SG_COUNTRY_CODE + phone),
                new PayNowField(EDITABLE_ID, EDITABLE)
        );
    }
}
