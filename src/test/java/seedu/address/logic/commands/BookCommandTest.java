package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Theme;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.booking.Booking;
import seedu.address.model.person.Person;
import seedu.address.testutil.BookingBuilder;


public class BookCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_bookingAcceptedByModel_addSuccessful() throws Exception {
        BookCommandTest.ModelStubAcceptingBookingAdded modelStub = new BookCommandTest.ModelStubAcceptingBookingAdded();
        Booking validBooking = new BookingBuilder().build();

        CommandResult commandResult = new BookCommand(validBooking).execute(modelStub);

        assertEquals(String.format(BookCommand.MESSAGE_SUCCESS, Messages.formatBooking(validBooking)),
                     commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBooking), modelStub.bookingsAdded);
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
        public void addBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void cancelBooking(Booking target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBooking(Booking booking) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Booking> getFilteredBookingList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTheme(Theme theme) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBookingList(Predicate<Booking> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single booking.
     */
    private class ModelStubWithBooking extends BookCommandTest.ModelStub {
        private final Booking booking;

        ModelStubWithBooking(Booking booking) {
            requireNonNull(booking);
            this.booking = booking;
        }

        @Override
        public boolean hasBooking(Booking booking) {
            requireNonNull(booking);
            return this.booking.equals(booking);
        }
    }

    /**
     * A Model stub that always accept the booking being added.
     */
    private class ModelStubAcceptingBookingAdded extends BookCommandTest.ModelStub {
        final ArrayList<Booking> bookingsAdded = new ArrayList<>();

        @Override
        public boolean hasBooking(Booking booking) {
            requireNonNull(booking);
            return bookingsAdded.stream().anyMatch(booking::equals);
        }

        @Override
        public void addBooking(Booking booking) {
            requireNonNull(booking);
            bookingsAdded.add(booking);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
