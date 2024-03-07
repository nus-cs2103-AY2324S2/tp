package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;
import seedu.address.model.person.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static final Remark EMPTY_REMARK = new Remark("");

    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                getTags("friends"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 2, 19, 21, 30), new Description("General Flu")),
                        new Note(LocalDateTime.of(2024, 2, 28, 8, 30), new Description("Headache")),
                    })),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), EMPTY_REMARK,
                getTags("colleagues", "friends"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 1, 2, 10, 0), new Description("Annual physical exam")),
                        new Note(LocalDateTime.of(2024, 3, 15, 9, 15), new Description("Follow-up for blood pressure")),
                        new Note(LocalDateTime.of(2024, 6, 7, 15, 0), new Description("Dermatology consultation"))
                    })),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                getTags("neighbours"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2023, 2, 10, 14, 0), new Description("Vision checkup")),
                        new Note(LocalDateTime.of(2023, 5, 23, 9, 0), new Description("Stomach pain evaluation")),
                        new Note(LocalDateTime.of(2024, 6, 6, 11, 15), new Description("Prenatal checkup")),
                    })),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                getTags("family"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 2, 20, 15, 30), new Description("Joint pain assessment")),
                        new Note(LocalDateTime.of(2024, 4, 4, 10, 30), new Description("Post-surgery checkup")),
                        new Note(LocalDateTime.of(2024, 5, 19, 17, 0), new Description("Sports injury follow-up")),
                    })),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                getTags("classmates"), getNotes(new Note[]{})),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                getTags("colleagues"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2023, 8, 16, 14, 45), new Description("Mental health consultation")),
                        new Note(LocalDateTime.of(2024, 1, 28, 10, 15), new Description("Blood sugar review")),
                        new Note(LocalDateTime.of(2024, 4, 5, 16, 0), new Description("Sore throat and fever")),
                    }))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTags(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

    /**
     * Returns a note list containing the list of notes given.
     */
    public static ObservableList<Note> getNotes(Note[] notes) {
        return Arrays.stream(notes)
            .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

}
