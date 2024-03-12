package seedu.address.model;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.ContactName;
import seedu.address.model.internship.ContactEmail;
import seedu.address.model.internship.ContactNumber;
import seedu.address.model.internship.Location;
import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.Description;
import seedu.address.model.internship.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InternshipDataTest {
    private final InternshipData data = new InternshipData();

    @Test
    public void testAddInternship() {
        Internship internship = new Internship(
                new CompanyName("Amazon"),
                new ContactName("Mark Johnson"),
                new ContactEmail("markjohnson@example.com"),
                new ContactNumber("45678901"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Business Development Internship"),
                new Role("Business Development Associate")
        );

        data.addInternship(internship);
        assertTrue(data.hasInternship(internship));
    }

    @Test
    public void testRemoveInternship() {
        Internship internship = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        data.removeInternship(internship);

        assertFalse(data.hasInternship(internship));
    }

    @Test
    public void testSetInternships() {
        InternshipData data = new InternshipData();
        Internship internship1 = new Internship(
                new CompanyName("Facebook"),
                new ContactName("Alice Smith"),
                new ContactEmail("alicesmith@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Marketing Internship"),
                new Role("Marketing Intern")
        );
        Internship internship2 = new Internship(
                new CompanyName("Microsoft"),
                new ContactName("Bob Brown"),
                new ContactEmail("bobbrown@example.com"),
                new ContactNumber("87654321"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Development Internship"),
                new Role("Software Developer")
        );
        List<Internship> internships = new ArrayList<>();
        internships.add(internship1);
        internships.add(internship2);

        data.setInternships(internships);

        assertTrue(data.hasInternship(internship1));
        assertTrue(data.hasInternship(internship2));
    }

    @Test
    public void testResetData() {
        Internship internship = new Internship(
                new CompanyName("Apple"),
                new ContactName("Eve Green"),
                new ContactEmail("evegreen@example.com"),
                new ContactNumber("13579246"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Product Management Internship"),
                new Role("Product Manager")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        InternshipData newData = new InternshipData();
        Internship newInternship = new Internship(
                new CompanyName("Tesla"),
                new ContactName("Elon Musk"),
                new ContactEmail("elonmusk@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Engineering Internship"),
                new Role("Engineering Intern")
        );
        newData.addInternship(newInternship);

        data.resetData(newData);

        assertFalse(data.hasInternship(internship));
        assertTrue(data.hasInternship(newInternship));
    }

    @Test
    public void testHasInternship() {
        Internship internship = new Internship(
                new CompanyName("SpaceX"),
                new ContactName("Elon Musk"),
                new ContactEmail("elonmusk@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Engineering Internship"),
                new Role("Engineering Intern")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship);

        assertTrue(data.hasInternship(internship));
    }

    @Test
    public void testSetInternship() {
        Internship internship1 = new Internship(
                new CompanyName("Amazon"),
                new ContactName("Mark Johnson"),
                new ContactEmail("markjohnson@example.com"),
                new ContactNumber("45678901"),
                new Location("remote"),
                new ApplicationStatus("rejected"),
                new Description("Business Development Internship"),
                new Role("Business Development Associate")
        );
        Internship internship2 = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        InternshipData data = new InternshipData();
        data.addInternship(internship1);

        data.setInternship(internship1, internship2);

        assertFalse(data.hasInternship(internship1));
        assertTrue(data.hasInternship(internship2));
    }

    @Test
    public void testEquals() {
        InternshipData data1 = new InternshipData();
        InternshipData data2 = new InternshipData();

        assertEquals(data1, data2);
    }

    @Test
    public void testGetInternshipList() {
        InternshipData data = new InternshipData();
        ObservableList<Internship> internshipList = data.getInternshipList();

        assertNotNull(internshipList);
        assertTrue(internshipList.isEmpty());
    }

    @Test
    public void testToString() {
        InternshipData data = new InternshipData();
        Internship internship = new Internship(
                new CompanyName("LinkedIn"),
                new ContactName("Mary Brown"),
                new ContactEmail("marybrown@example.com"),
                new ContactNumber("13579246"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Data Analytics Internship"),
                new Role("Data Analyst")
        );
        data.addInternship(internship);

        String expected = "seedu.address.model.InternshipData{Internship list=[seedu.address.model.internship.Internship{companyName=LinkedIn, contactName=Mary Brown, contactEmail=marybrown@example.com, contactNumber=13579246, location=REMOTE, applicationStatus=PENDING, description=Data Analytics Internship, role=Data Analyst}]}";
        assertEquals(expected, data.toString());
    }
}


