package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMISSION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMPLOYMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.messages.EditMessages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditMaintainerDescriptorBuilder;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.EditSupplierDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_PRODUCT_AMY = "pooch food";
    public static final String VALID_PRICE_AMY = "$50/bag";
    public static final String VALID_SKILL_AMY = "trian dog";
    public static final String VALID_COMMISSION_AMY = "$50/hr";
    public static final String VALID_SALARY_AMY = "$50/hr";
    public static final String VALID_EMPLOYMENT_AMY = "part-time";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";

    public static final String VALID_NOTE_AMY = "Cancel shipment with amy";
    public static final String VALID_NOTE_BOB = "Cancel shipment with bob";
    public static final String VALID_PRODUCT_BOB = "pooch food";
    public static final String VALID_PRICE_BOB = "$50/bag";
    public static final String VALID_SKILL_BOB = "trian dog";
    public static final String VALID_COMMISSION_BOB = "$50/hr";
    public static final String VALID_SALARY_BOB = "$50/hr";
    public static final String VALID_EMPLOYMENT_BOB = "part-time";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG = "other";
    public static final String VALID_TAG_STAFF = "staff";
    public static final String VALID_TAG_SUPPLIER = "supplier";
    public static final String VALID_TAG_MAINTAINER = "maintainer";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String NOTE_DESC_BOB = " " + PREFIX_NOTE + "Cancel shipment with bob";
    public static final String RATING_DESC_BOB = " " + PREFIX_RATING + "0";
    public static final String DEADLINE_DESC_BOB = " " + PREFIX_DEADLINE + "2019-10-10";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String SALARY_DESC_AMY = " " + PREFIX_SALARY + VALID_SALARY_AMY;
    public static final String EMPLOYMENT_DESC_AMY = " " + PREFIX_EMPLOYMENT + VALID_EMPLOYMENT_AMY;
    public static final String PRODUCT_DESC_AMY = " " + PREFIX_PRODUCT + VALID_PRODUCT_AMY;
    public static final String PRICE_DESC_AMY = " " + PREFIX_PRICE + VALID_PRICE_AMY;
    public static final String SKILL_DESC_AMY = " " + PREFIX_SKILL + VALID_SKILL_AMY;
    public static final String COMMISSION_DESC_AMY = "  " + PREFIX_COMMISSION + VALID_COMMISSION_AMY;
    public static final String SALARY_DESC_BOB = " " + PREFIX_SALARY + VALID_SALARY_BOB;
    public static final String EMPLOYMENT_DESC_BOB = " " + PREFIX_EMPLOYMENT + VALID_EMPLOYMENT_BOB;
    public static final String PRODUCT_DESC_BOB = " " + PREFIX_PRODUCT + VALID_PRODUCT_BOB;
    public static final String PRICE_DESC_BOB = " " + PREFIX_PRICE + VALID_PRICE_BOB;
    public static final String SKILL_DESC_BOB = " " + PREFIX_SKILL + VALID_SKILL_BOB;
    public static final String COMMISSION_DESC_BOB = "  " + PREFIX_COMMISSION + VALID_COMMISSION_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME = "invalid name here";

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_RATING_DESC = " " + PREFIX_RATING; // empty string not allowed for ratings
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_SALARY_DESC = " " + PREFIX_SALARY + "20";
    public static final String INVALID_EMPLOYMENT_DESC = " " + PREFIX_EMPLOYMENT + "employment";
    public static final String INVALID_PRODUCT_DESC = " " + PREFIX_PRODUCT;
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + "20";
    public static final String INVALID_SKILL_DESC = " " + PREFIX_SKILL;
    public static final String INVALID_COMMISSION_DESC = " " + PREFIX_COMMISSION + "20";
    public static final String INVALID_DESC_BOB = " " + PREFIX_NOTE;
    public static final String INVALID_DEADLINENOTE_BOB = " " + PREFIX_NOTE + "20";
    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditStaffCommand.EditStaffDescriptor DESC_AMY_STAFF;
    public static final EditStaffCommand.EditStaffDescriptor DESC_BOB_STAFF;
    public static final EditSupplierCommand.EditSupplierDescriptor DESC_AMY_SUPPLIER;
    public static final EditSupplierCommand.EditSupplierDescriptor DESC_BOB_SUPPLIER;
    public static final EditMaintainerCommand.EditMaintainerDescriptor DESC_AMY_MAINTAINER;
    public static final EditMaintainerCommand.EditMaintainerDescriptor DESC_BOB_MAINTAINER;


    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_AMY_SUPPLIER = new EditSupplierDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withPrice(VALID_PRICE_AMY).withProduct(VALID_PRODUCT_AMY)
                .withTags(VALID_TAG_SUPPLIER).build();
        DESC_BOB_SUPPLIER = new EditSupplierDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withPrice(VALID_PRICE_BOB).withProduct(VALID_PRODUCT_BOB)
                .withTags(VALID_TAG_SUPPLIER).build();
        DESC_AMY_STAFF = new EditStaffDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withEmployment(VALID_EMPLOYMENT_AMY).withSalary(VALID_SALARY_AMY)
                .withTags(VALID_TAG_SUPPLIER).build();
        DESC_BOB_STAFF = new EditStaffDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withEmployment(VALID_EMPLOYMENT_BOB).withSalary(VALID_SALARY_BOB)
                .withTags(VALID_TAG_STAFF).build();
        DESC_AMY_MAINTAINER = new EditMaintainerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withSkill(VALID_SKILL_AMY).withCommission(VALID_COMMISSION_AMY)
                .withTags(VALID_TAG_SUPPLIER).build();
        DESC_BOB_MAINTAINER = new EditMaintainerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withSkill(VALID_SKILL_BOB).withCommission(VALID_COMMISSION_BOB)
                .withTags(VALID_TAG_STAFF).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonWithName(Model model, Name targetName) {
        assertTrue(targetName != null);

        Person person;

        try {
            person = model.findPersonByName(targetName, EditMessages.MESSAGE_INVALID_EDIT_PERSON);
            final String[] splitName = person.getName().fullName.split("\\s+");
            model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

            assertEquals(1, model.getFilteredPersonList().size());
        } catch (CommandException e) {
            e.printStackTrace();
        }
    }

}
