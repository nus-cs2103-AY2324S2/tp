package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.ALICEMAINTAINER;
import static seedu.address.testutil.TypicalPersons.ALICESTAFF;
import static seedu.address.testutil.TypicalPersons.ALICESUPPLIER;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Maintainer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Staff;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.MaintainerBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.StaffBuilder;
import seedu.address.testutil.SupplierBuilder;

public class PinCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validPinOther_pinSuccess() throws CommandException {
        Person personToPin = new PersonBuilder(ALICE).withName("Pin Test Alice Person")
                .withAddress(VALID_ADDRESS_BOB).build();
        Person expectedPerson = new PersonBuilder(ALICE).withName("Pin Test Alice Person")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedPerson.toPin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        expectedModel.updatePinnedPersonList();

        model.addPerson(personToPin);
        PinCommand pinCommand = new PinCommand(personToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinStaff_pinSuccess() throws CommandException {
        Staff staffToPin = new StaffBuilder(ALICESTAFF).withName("Pin Test Alice Staff")
                .withAddress(VALID_ADDRESS_BOB).build();
        Staff expectedStaff = new StaffBuilder(ALICESTAFF).withName("Pin Test Alice Staff")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedStaff.toPin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedStaff);
        expectedModel.updatePinnedPersonList();

        model.addPerson(staffToPin);
        PinCommand pinCommand = new PinCommand(staffToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinMaintainer_pinSuccess() throws CommandException {
        Maintainer maintainerToPin = new MaintainerBuilder(ALICEMAINTAINER)
                .withName("Pin Test Alice Maintainer").withAddress(VALID_ADDRESS_BOB).build();
        Maintainer expectedMaintainer = new MaintainerBuilder(ALICEMAINTAINER)
                .withName("Pin Test Alice Maintainer").withAddress(VALID_ADDRESS_BOB).build();
        expectedMaintainer.toPin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedMaintainer);
        expectedModel.updatePinnedPersonList();

        model.addPerson(maintainerToPin);
        PinCommand pinCommand = new PinCommand(maintainerToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validPinSupplier_pinSuccess() throws CommandException {
        Supplier supplierToPin = new SupplierBuilder(ALICESUPPLIER).withName("Pin Test Alice Supplier")
                .withAddress(VALID_ADDRESS_BOB).build();
        Supplier expectedSupplier = new SupplierBuilder(ALICESUPPLIER).withName("Pin Test Alice Supplier")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedSupplier.toPin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedSupplier);
        expectedModel.updatePinnedPersonList();

        model.addPerson(supplierToPin);
        PinCommand pinCommand = new PinCommand(supplierToPin.getName());

        pinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
