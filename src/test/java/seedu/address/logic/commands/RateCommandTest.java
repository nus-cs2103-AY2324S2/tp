package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGIAMAINTAINER;
import static seedu.address.testutil.TypicalPersons.GEORGIASTAFF;
import static seedu.address.testutil.TypicalPersons.GEORGIASUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.RateMessages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;

public class RateCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Rating validRating1 = new Rating("1");

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
        model.addPerson(GEORGIASTAFF);

        Staff toAddRatingStaff = GEORGIASTAFF;
        Staff expectedStaff = new Staff(toAddRatingStaff.getName(), toAddRatingStaff.getPhone(),
                toAddRatingStaff.getEmail(), toAddRatingStaff.getAddress(), toAddRatingStaff.getNote(),
                toAddRatingStaff.getTags(), toAddRatingStaff.getSalary(), toAddRatingStaff.getEmployment(),
                toAddRatingStaff.getRating());
        expectedStaff.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedStaff);

        RateCommand rateCommand = new RateCommand(toAddRatingStaff.getName(), validRating1);

        try {
            rateCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validRatingSupplier_addSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(GEORGIASUPPLIER);

        Supplier toAddRatingSupplier = GEORGIASUPPLIER;
        Supplier expectedSupplier = new Supplier(toAddRatingSupplier.getName(), toAddRatingSupplier.getPhone(),
                toAddRatingSupplier.getEmail(), toAddRatingSupplier.getAddress(), toAddRatingSupplier.getNote(),
                toAddRatingSupplier.getTags(), toAddRatingSupplier.getProduct(), toAddRatingSupplier.getPrice(),
                toAddRatingSupplier.getRating());
        expectedSupplier.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedSupplier);

        RateCommand rateCommand = new RateCommand(toAddRatingSupplier.getName(), validRating1);

        try {
            rateCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validRatingMaintainer_addSuccess() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addPerson(GEORGIAMAINTAINER);

        Maintainer toAddRatingMaintainer = GEORGIAMAINTAINER;
        Maintainer expectedMaintainer = new Maintainer(toAddRatingMaintainer.getName(),
                toAddRatingMaintainer.getPhone(), toAddRatingMaintainer.getEmail(), toAddRatingMaintainer.getAddress(),
                toAddRatingMaintainer.getNote(), toAddRatingMaintainer.getTags(), toAddRatingMaintainer.getSkill(),
                toAddRatingMaintainer.getCommission(), toAddRatingMaintainer.getRating());
        expectedMaintainer.setNoteContent(validRating1.toString());
        expectedModel.addPerson(expectedMaintainer);

        RateCommand rateCommand = new RateCommand(toAddRatingMaintainer.getName(), validRating1);

        try {
            rateCommand.execute(model);
        } catch (CommandException ce) {
            fail();
        }

        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
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
