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

public class UnpinCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validUnpinOther_unpinSuccess() throws CommandException {
        Person personToUnpin = new PersonBuilder(ALICE).withName("Unpin Test Alice Person")
                .withAddress(VALID_ADDRESS_BOB).build();
        personToUnpin.toPin();

        Person expectedPerson = new PersonBuilder(ALICE).withName("Unpin Test Alice Person")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedPerson.toPin();
        expectedPerson.toUnpin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedPerson);
        expectedModel.updatePinnedPersonList();

        model.addPerson(personToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedPerson.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinSupplier_unpinSuccess() throws CommandException {
        Supplier supplierToUnpin = new SupplierBuilder(ALICESUPPLIER).withName("Unpin Test Alice Supplier")
                .withAddress(VALID_ADDRESS_BOB).build();
        supplierToUnpin.toPin();

        Supplier expectedSupplier = new SupplierBuilder(ALICESUPPLIER).withName("Unpin Test Alice Supplier")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedSupplier.toPin();
        expectedSupplier.toUnpin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedSupplier);
        expectedModel.updatePinnedPersonList();

        model.addPerson(supplierToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedSupplier.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinMaintainer_unpinSuccess() throws CommandException {
        Maintainer maintainerToUnpin = new MaintainerBuilder(ALICEMAINTAINER)
                .withName("Unpin Test Alice Maintainer").withAddress(VALID_ADDRESS_BOB).build();
        maintainerToUnpin.toPin();

        Maintainer expectedMaintainer = new MaintainerBuilder(ALICEMAINTAINER)
                .withName("Unpin Test Alice Maintainer").withAddress(VALID_ADDRESS_BOB).build();
        expectedMaintainer.toPin();
        expectedMaintainer.toUnpin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedMaintainer);
        expectedModel.updatePinnedPersonList();

        model.addPerson(maintainerToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedMaintainer.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_validUnpinStaff_unpinSuccess() throws CommandException {
        Staff staffToUnpin = new StaffBuilder(ALICESTAFF).withName("Unpin Test Alice Staff")
                .withAddress(VALID_ADDRESS_BOB).build();
        staffToUnpin.toPin();

        Staff expectedStaff = new StaffBuilder(ALICESTAFF).withName("Unpin Test Alice Staff")
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedStaff.toPin();
        expectedStaff.toUnpin();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(expectedStaff);
        expectedModel.updatePinnedPersonList();

        model.addPerson(staffToUnpin);
        UnpinCommand unpinCommand = new UnpinCommand(expectedStaff.getName());

        unpinCommand.execute(model);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }
}
