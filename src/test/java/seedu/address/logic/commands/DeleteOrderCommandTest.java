package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.orders.DeleteOrderCommand.MESSAGE_SUCCESS;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.orders.DeleteOrderCommand;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.order.OrderList;
import seedu.address.model.person.Person;
import seedu.address.testutil.OrderBuilder;
public class DeleteOrderCommandTest {

    @Test
    public void execute_deleteOrderByModel_deleteSuccessful() throws Exception {
        OrderBuilder builder = new OrderBuilder();
        Order order = builder.build();
        ModelStubDeletingOrder modelStub = new ModelStubDeletingOrder(order);
        CommandResult commandResult = new DeleteOrderCommand(order.getOrderId()).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Messages.format(order)),
                commandResult.getFeedbackToUser());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getOrderList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that always accepts the order being added.
     */
    private class ModelStubDeletingOrder extends ModelStub {
        private final Order order;

        ModelStubDeletingOrder(Order order) {
            requireNonNull(order);
            this.order = order;
        }

        @Override
        public void deleteOrder(Order order) {
            requireNonNull(order);
        }

        @Override
        public ObservableList<Order> getOrderList() {
            OrderList orderList = new OrderList();
            orderList.add(order);
            return orderList.asUnmodifiableObservableList();
        }
    }
}
