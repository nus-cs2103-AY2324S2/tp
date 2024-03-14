package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.InternshipData;
import seedu.address.model.internship.Internship;

/**
 * A utility class containing a list of {@code Internship} objects to be used in tests.
 */
public class TypicalInternships {

    public static final Internship ALICE_MICROSOFT = new InternshipBuilder().withCompanyName("Microsoft")
            .withContactName("Alice Pauline").withContactEmail("alice@example.com").withContactNumber("94351253")
            .withApplicationStatus("ongoing").withLocation("remote")
            .withDescription("Use Figma to design User-friendly web interfaces").withRole("Frontend Engineer")
            .build();

    public static final Internship BENSON_GOOGLE = new InternshipBuilder().withCompanyName("Google")
            .withContactName("Benson Meier").withContactEmail("benson@example.com").withContactNumber("98765432")
            .withLocation("local").withApplicationStatus("pending").withDescription("Write REST APIs for services")
            .withRole("Backend Engineer").build();

    public static final Internship CARL_OPENAI = new InternshipBuilder().withCompanyName("OpenAI")
            .withContactName("Carl Kurz").withContactEmail("carl@google.com").withContactNumber("95352563")
            .withLocation("overseas").withApplicationStatus("rejected").withDescription("Enter prompts into ChatGPT")
            .withRole("Prompt Engineer").build();

    // Manually added
    public static final Internship HOON_APPLE = new InternshipBuilder().withCompanyName("Apple")
            .withContactName("Hoon Meier").withContactEmail("hoon@gmail.com").withContactNumber("8482424")
            .withLocation("local").withApplicationStatus("pending").withDescription("Develop iOS applications")
            .withRole("iOS Developer").build();

    public static final Internship IDA_NETFLIX = new InternshipBuilder().withCompanyName("Netflix")
            .withContactName("Ida Mueller").withContactEmail("ida@example.com").withContactNumber("8482131")
            .withLocation("remote").withApplicationStatus("ongoing").withDescription("Write REST APIs for netflix")
            .withRole("Backend Engineer").build();

    private TypicalInternships() {
    } // prevents instantiation

    /**
     * Returns an {@code InternshipData} with all the typical internships.
     */
    public static InternshipData getTypicalInternshipData() {
        InternshipData id = new InternshipData();
        for (Internship internship : getTypicalInternships()) {
            id.addInternship(internship);
        }
        return id;
    }

    public static List<Internship> getTypicalInternships() {
        return new ArrayList<>(Arrays.asList(ALICE_MICROSOFT, BENSON_GOOGLE, CARL_OPENAI));
    }
}
