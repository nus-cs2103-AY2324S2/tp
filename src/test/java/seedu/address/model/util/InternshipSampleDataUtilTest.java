package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternshipData;
import seedu.address.model.internship.Internship;

public class InternshipSampleDataUtilTest {

    @Test
    public void getSampleInternships() {
        Internship[] sampleInternships = InternshipSampleDataUtil.getSampleInternships();
        assertEquals(3, sampleInternships.length);
        assertEquals("Google", sampleInternships[0].getCompanyName().toString());
        assertEquals("Facebook", sampleInternships[1].getCompanyName().toString());
        assertEquals("Amazon", sampleInternships[2].getCompanyName().toString());
    }

    @Test
    public void getSampleInternshipData() {
        InternshipData sampleInternshipData = (InternshipData) InternshipSampleDataUtil.getSampleInternshipData();
        assertEquals(3, sampleInternshipData.getInternshipList().size());
        assertEquals("Google", sampleInternshipData.getInternshipList().get(0).getCompanyName().toString());
        assertEquals("Facebook", sampleInternshipData.getInternshipList().get(1).getCompanyName().toString());
        assertEquals("Amazon", sampleInternshipData.getInternshipList().get(2).getCompanyName().toString());
    }

    @Test
    public void getTagSet() {
        assertEquals(3, InternshipSampleDataUtil.getTagSet("tag1", "tag2", "tag3").size());
        assertEquals(0, InternshipSampleDataUtil.getTagSet().size());
    }
}
