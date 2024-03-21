package tutorpro.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tutorpro.model.person.parent.Parent;
import tutorpro.model.util.SampleDataUtil;

public class TypicalParents {
    public static final Parent JUCHIE = new ParentBuilder().withName("Ju Chie")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("jc@example.com")
            .withPhone("94351253")
            .withTags("friends")
            .withStudents(SampleDataUtil.getSampleStudents())
            .build();

    private TypicalParents() {} // prevents instantiation

    /**
     * Returns an {@code List<Person>} with all the typical parents.
     */
    public static List<Parent> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(JUCHIE));
    }
}
