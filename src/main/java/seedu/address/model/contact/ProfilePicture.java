package seedu.address.model.contact;

import java.util.Objects;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ProfilePicture {
    public final static String DEFAULT_URL = "https://api-prod-minimal-v510.vercel.app/assets/images/avatar/avatar_13.jpg";
    String url;
    public ProfilePicture(String url) {
        this.url = url;
    }

    public String get() {
        return this.url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // instanceof handles nulls
        if (!(o instanceof ProfilePicture)) {
            return false;
        }        ProfilePicture that = (ProfilePicture) o;
        return Objects.equals(this.url, that.url);
    }



    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return url;
    }
}
