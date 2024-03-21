package seedu.address.model.contact;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProfilePictureTest {

    @Test
    public void constructor_validUrl_success() {
        String validUrl = "https://example.com/image.jpg";
        ProfilePicture profilePicture = new ProfilePicture(validUrl);
        assertEquals(validUrl, profilePicture.get());
    }

    @Test
    public void constructor_blankUrl_returnsDefaultUrl() {
        ProfilePicture profilePicture = new ProfilePicture("");
        assertEquals(ProfilePicture.DEFAULT_URL, profilePicture.get());
    }

    @Test
    public void constructor_nullUrl_returnsDefaultUrl() {
        ProfilePicture profilePicture = new ProfilePicture(null);
        assertEquals(ProfilePicture.DEFAULT_URL, profilePicture.get());
    }

    @Test
    public void equals_sameUrl_returnsTrue() {
        String url = "https://example.com/image.jpg";
        ProfilePicture profilePicture1 = new ProfilePicture(url);
        ProfilePicture profilePicture2 = new ProfilePicture(url);
        assertEquals(profilePicture1, profilePicture2);
    }

    @Test
    public void equals_differentUrls_returnsFalse() {
        ProfilePicture profilePicture1 = new ProfilePicture("https://example.com/image1.jpg");
        ProfilePicture profilePicture2 = new ProfilePicture("https://example.com/image2.jpg");
        assertEquals(false, profilePicture1.equals(profilePicture2));
    }
}
