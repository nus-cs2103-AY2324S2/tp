package vitalconnect.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import vitalconnect.model.Clinic;
import vitalconnect.model.ReadOnlyClinic;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.identificationinformation.IdentificationInformation;
import vitalconnect.model.person.identificationinformation.Name;
import vitalconnect.model.person.identificationinformation.Nric;
import vitalconnect.model.tag.Tag;

/**
 * Contains utility methods for populating {@code Clinic} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new IdentificationInformation(new Name("Alex Yeoh"), new Nric("G7654321L")),
                getTagSet("friends")),
            new Person(new IdentificationInformation(new Name("Bernice Yu"), new Nric("M7654321J")),
                getTagSet("colleagues"))
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
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
