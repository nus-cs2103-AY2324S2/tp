package vitalconnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import vitalconnect.model.Clinic;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.allergytag.AllergyTag;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;

/**
 * Contains utility methods for populating {@code Clinic} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new IdentificationInformation(new Name("Alex Yeoh"), new Nric("G7654321L"))),
            new Person(new IdentificationInformation(new Name("Bernice Yu"), new Nric("M7654321J")))
        };
    }

    public static ReadOnlyClinic getSampleClinic() {
        Clinic sampleAb = new Clinic();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a allergytag set containing the list of strings given.
     */
    public static Set<AllergyTag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(AllergyTag::new)
                .collect(Collectors.toSet());
    }

}
