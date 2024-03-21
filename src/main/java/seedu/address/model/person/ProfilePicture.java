package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ProfilePicture {
    String DEFAULT_URL = "https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_13.jpg";
    String url;
    public ProfilePicture(String url) {
        this.url = url;
    }

    public String get() {
        if (this.url.isBlank()) {
            return DEFAULT_URL;
        }
        return this.url;
    }


}
