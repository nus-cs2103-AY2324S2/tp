package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.patient.FamilyCondition;
import seedu.address.model.patient.FoodPreference;
import seedu.address.model.patient.Hobby;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.PatientHospitalId;
import seedu.address.model.patient.PreferredName;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new PatientHospitalId("12345"), new Name("Alex Yeoh Jia Jun"), new PreferredName("Alex"),
                new FoodPreference("Curry chicken"),
                new FamilyCondition("Stable, Has 2 sons that visits him regularly"),
                new Hobby("Singing karaoke"),
                getTagSet("Diabetes")),
            new Patient(new PatientHospitalId("12346"), new Name("Bernice Yu Sheng Huat"), new PreferredName("Bern"),
                new FoodPreference("Char Kuay Tiao, Black Carrot Cake"),
                new FamilyCondition("Lives alone, no family members around"), new Hobby("Playing Mahjong"),
                getTagSet("High Blood Pressure")),
            new Patient(new PatientHospitalId("12347"), new Name("Mary Jane"), new PreferredName("Mary"),
                new FoodPreference("Korean"),
                new FamilyCondition("Lives with only daughter, quarrels regularly with daughter"),
                new Hobby("Watching Drama"),
                getTagSet("Heart attack")),
            new Patient(new PatientHospitalId("12348"), new Name("David Li"), new PreferredName("David"),
                new FoodPreference("Bak Kut Teh"), new FamilyCondition("Son visits him every weekend"),
                new Hobby("Plays erhu"),
                getTagSet("Sleeping disorder", "Skin sensitive")),
            new Patient(new PatientHospitalId("12349"), new Name("Irfan Ibrahim"), new PreferredName("Fan"),
                new FoodPreference("Roti Prata"), new FamilyCondition("Children encountered accident 2 months ago"),
                new Hobby("Plays badminton"),
                getTagSet("Difficulty in swallowing")),
            new Patient(new PatientHospitalId("12350"), new Name("Roy Balakrishnan"), new PreferredName("Rony"),
                new FoodPreference("Fish Ball Soup"), new FamilyCondition("Financial problem"),
                new Hobby("Jog around park"),
                getTagSet("wheelchair")),
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
