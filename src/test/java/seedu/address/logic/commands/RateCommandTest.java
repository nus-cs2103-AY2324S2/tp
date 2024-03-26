package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.messages.RateMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class RateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Rating validRating1 = new Rating("1");
    private final Rating validRating2 = new Rating("2");

    private final Staff georgiaStaff = new StaffBuilder().withName("Georgia Staff")
            .withAddress("123, Jurong West Ave 45, #08-131").withEmail("georgia@example.com")
            .withPhone("94355453")
            .withTags("friends")
            .withSalary("$50/hr")
            .withEmployment("part-time").build();

    private final Supplier georgiaSupplier = new SupplierBuilder().withName("Georgia Supplier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withProduct("pooch medicine")
            .withPrice("$50/injection").build();

    private final Maintainer georgiaMaintainer = new MaintainerBuilder().withName("Georgia Maintainer")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("georgia@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends")
            .withSkill("train dog")
            .withCommission("$50/hr").build();

    @Test
    public void execute_validRatingOther_addSuccess() {
        Person toAddRatingPerson = ALICE;
        Person expectedPerson = new Person(toAddRatingPerson.getName(), toAddRatingPerson.getPhone(),
                toAddRatingPerson.getEmail(), toAddRatingPerson.getAddress(),
                toAddRatingPerson.getNote(), toAddRatingPerson.getTags(), validRating1);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        RateCommand rateCommand = new RateCommand(toAddRatingPerson.getName(), validRating1);
        String expectedMessage = String.format(RateMessages.MESSAGE_RATE_PERSON_SUCCESS,
                RateMessages.format(expectedPerson));

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRatingStaff_addSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(georgiaStaff);

        Staff toAddRatingPerson = georgiaStaff;
        Staff expectedPerson = new Staff(toAddRatingPerson.getName(), toAddRatingPerson.getPhone(),
                toAddRatingPerson.getEmail(), toAddRatingPerson.getAddress(), toAddRatingPerson.getNote(),
                toAddRatingPerson.getTags(), toAddRatingPerson.getSalary(), toAddRatingPerson.getEmployment(),
                validRating1);
        expectedPerson.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedPerson);


        RateCommand rateCommand = new RateCommand(toAddRatingPerson.getName(), validRating1);
        String expectedMessage = String.format(RateMessages.MESSAGE_RATE_PERSON_SUCCESS,
                RateMessages.format(expectedPerson));

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRatingSupplier_addSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(georgiaSupplier);

        Supplier toAddRatingPerson = georgiaSupplier;
        Supplier expectedPerson = new Supplier(toAddRatingPerson.getName(), toAddRatingPerson.getPhone(),
                toAddRatingPerson.getEmail(), toAddRatingPerson.getAddress(), toAddRatingPerson.getNote(),
                toAddRatingPerson.getTags(), toAddRatingPerson.getProduct(), toAddRatingPerson.getPrice(),
                validRating1);
        expectedPerson.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedPerson);


        RateCommand rateCommand = new RateCommand(toAddRatingPerson.getName(), validRating1);
        String expectedMessage = String.format(RateMessages.MESSAGE_RATE_PERSON_SUCCESS,
                RateMessages.format(expectedPerson));

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validRatingMaintainer_addSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(georgiaMaintainer);

        Maintainer toAddRatingPerson = georgiaMaintainer;
        Maintainer expectedPerson = new Maintainer(toAddRatingPerson.getName(), toAddRatingPerson.getPhone(),
                toAddRatingPerson.getEmail(), toAddRatingPerson.getAddress(), toAddRatingPerson.getNote(),
                toAddRatingPerson.getTags(), toAddRatingPerson.getSkill(), toAddRatingPerson.getCommission(),
                validRating1);
        expectedPerson.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedPerson);


        RateCommand rateCommand = new RateCommand(toAddRatingPerson.getName(), validRating1);
        String expectedMessage = String.format(RateMessages.MESSAGE_RATE_PERSON_SUCCESS,
                RateMessages.format(expectedPerson));

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }
    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(ALICE.getName(), validRating1);
        RateCommand rateSecondCommand = new RateCommand(BENSON.getName(), validRating1);

        // same object -> returns true
        assertEquals(rateFirstCommand, rateFirstCommand);

        // different names -> returns false
        assertNotEquals(rateFirstCommand, rateSecondCommand);
    }
}
