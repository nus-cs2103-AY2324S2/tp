package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueInternshipListTest {

    @Test
    public void testContains() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
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
        internshipList.add(internship);

        assertTrue(internshipList.contains(internship));
    }

    @Test
    public void testAddInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
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

        internshipList.add(internship);

        assertTrue(internshipList.contains(internship));
    }

    @Test
    public void testSetInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
        Internship originalInternship = new Internship(
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        Internship editedInternship = new Internship(
                new CompanyName("Google"),
                new ContactName("Jane Smith"),
                new ContactEmail("janesmith@example.com"),
                new ContactNumber("98765432"),
                new Location("remote"),
                new ApplicationStatus("accepted"),
                new Description("Product Management Internship"),
                new Role("Product Manager")
        );

        internshipList.add(originalInternship);
        internshipList.setInternship(originalInternship, editedInternship);

        assertFalse(internshipList.contains(originalInternship));
        assertTrue(internshipList.contains(editedInternship));
    }

    @Test
    public void testRemoveInternship() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
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
        internshipList.add(internship);

        internshipList.remove(internship);

        assertFalse(internshipList.contains(internship));
    }

    @Test
    public void testSetInternships() {
        UniqueInternshipList internshipList = new UniqueInternshipList();
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
        internshipList.add(internship1);

        UniqueInternshipList replacement = new UniqueInternshipList();
        replacement.add(internship2);

        internshipList.setInternships(replacement);

        assertFalse(internshipList.contains(internship1));
        assertTrue(internshipList.contains(internship2));
    }

    @Test
    public void testEquals() {
        UniqueInternshipList internshipList1 = new UniqueInternshipList();
        UniqueInternshipList internshipList2 = new UniqueInternshipList();
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
                new CompanyName("Google"),
                new ContactName("John Doe"),
                new ContactEmail("johndoe@example.com"),
                new ContactNumber("12345678"),
                new Location("remote"),
                new ApplicationStatus("pending"),
                new Description("Software Engineering Internship"),
                new Role("Software Engineer")
        );
        internshipList1.add(internship1);
        internshipList2.add(internship1);

        assertEquals(internshipList1, internshipList2);
    }
}
