//package seedu.address.testutil;
//
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import seedu.address.model.AddressBook;
//import seedu.address.model.person.Patient;
//import seedu.address.model.person.Person;
//
///**
// * A utility class containing a list of {@code Person} objects to be used in tests.
// */
//public class TypicalPatients {
//
//    public static final Patient ALICE = new PatientBuilder().withNRIC("S1234567A").withName("Alice Pauline")
//            .withDoB("2000-01-03").withPhone("94351253").build();
//    public static final Patient BENSON = new PatientBuilder().withNRIC("S8734985A").withName("Benson Chen")
//            .withDoB("2002-01-03").withPhone("88927639").build();
//
//    public static final Patient CARL = new PatientBuilder().withNRIC("S2378593A").withName("Carl Sim")
//            .withDoB("2005-01-03").withPhone("87436749").build();
//
//    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
//
//    private TypicalPatients() {} // prevents instantiation
//
//    /**
//     * Returns an {@code AddressBook} with all the typical persons.
//     */
//    public static AddressBook getTypicalAddressBook() {
//        AddressBook ab = new AddressBook();
//        for (Person person : getTypicalPersons()) {
//            ab.addPerson(person);
//        }
//        return ab;
//    }
//
//    public static List<Person> getTypicalPersons() {
//        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL));
//    }
//}
