package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.BirthDate;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.note.Description;
import seedu.address.model.person.note.Note;
import seedu.address.model.person.illness.Illness;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[]{
            new Person(new Nric("S1234567D"),new Name("Alex Yeoh"), new Gender("M"), new BirthDate("10-07-1999"),
                    new Phone("87438807"), new Email("alexyeoh@example.com"),
                getIllnesses("friends"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 2, 19, 21, 30),
                                new Description("General Flu")),
                        new Note(LocalDateTime.of(2024, 2, 28, 8, 30),
                                new Description("Headache")),
                    })),
            new Person(new Nric("S7654321A"), new Name("Bernice Yu"),
                    new Gender("F"), new BirthDate("05-07-1997"), new Phone("99272758"),
                    new Email("berniceyu@example.com"),
                getIllnesses("colleagues", "friends"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 1, 2, 10, 0),
                                new Description("Annual physical exam")),
                        new Note(LocalDateTime.of(2024, 3, 15, 9, 15),
                                new Description("Follow-up for blood pressure")),
                        new Note(LocalDateTime.of(2024, 6, 7, 15, 0),
                                new Description("Dermatology consultation"))
                    })),
            new Person(new Nric("S6543217B"), new Name("Charlotte Oliveiro"), new Gender("F"),
                    new BirthDate("06-07-1996"), new Phone("93210283"),
                    new Email("charlotte@example.com"),
                getIllnesses("neighbours"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2023, 2, 10, 14, 0),
                                new Description("Vision checkup")),
                        new Note(LocalDateTime.of(2023, 5, 23, 9, 0),
                                new Description("Stomach pain evaluation")),
                        new Note(LocalDateTime.of(2024, 6, 6, 11, 15),
                                new Description("Prenatal checkup")),
                    })),
            new Person(new Nric("S8375839G"), new Name("David Li"), new Gender("Prefer not to say"),
                    new BirthDate("02-02-2000"), new Phone("91031282"), new Email("lidavid@example.com"),
                getIllnesses("family"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2024, 2, 20, 15, 30),
                                new Description("Joint pain assessment")),
                        new Note(LocalDateTime.of(2024, 4, 4, 10, 30),
                                new Description("Post-surgery checkup")),
                        new Note(LocalDateTime.of(2024, 5, 19, 17, 0),
                                new Description("Sports injury follow-up")),
                    })),
            new Person(new Nric("S3837463H"), new Name("Irfan Ibrahim"), new Gender("M"),
                    new BirthDate("01-01-2001"), new Phone("92492021"), new Email("irfan@example.com"),
                getIllnesses("classmates"), getNotes(new Note[]{})),
            new Person(new Nric("S2393847D"), new Name("Roy Balakrishnan"), new Gender("M"),
                    new BirthDate("04-05-2007"), new Phone("92624417"), new Email("royb@example.com"),
                getIllnesses("colleagues"), getNotes(
                    new Note[]{
                        new Note(LocalDateTime.of(2023, 8, 16, 14, 45),
                                new Description("Mental health consultation")),
                        new Note(LocalDateTime.of(2024, 1, 28, 10, 15),
                                new Description("Blood sugar review")),
                        new Note(LocalDateTime.of(2024, 4, 5, 16, 0),
                                new Description("Sore throat and fever")),
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
     * Returns an illness set containing the list of strings given.
     */
    public static Set<Illness> getIllnesses(String... strings) {
        return Arrays.stream(strings)
            .map(Illness::new)
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
