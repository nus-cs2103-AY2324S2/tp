package seedu.address.model.contact;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ProfilePicture {
    final static String DEFAULT_URL = "https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_13.jpg";
    String url;
    public ProfilePicture(String url) {
        this.url = url;
    }

    public String get() {
        if (this.url == null || this.url.isBlank()) {
            return DEFAULT_URL;
        }
        return this.url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfilePicture that = (ProfilePicture) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
