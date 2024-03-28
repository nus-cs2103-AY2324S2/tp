package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.ReadOnlyNetConnect;
import seedu.address.model.person.Person;
import seedu.address.model.person.Products;
import seedu.address.model.person.Skills;
import seedu.address.model.tag.Tag;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons_validData_success() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();
        assertNotNull(samplePersons);
        assertEquals(6, samplePersons.length);
    }

    @Test
    public void getSampleNetConnect_validData_success() {
        ReadOnlyNetConnect sampleNetConnect = SampleDataUtil.getSampleNetConnect();
        assertNotNull(sampleNetConnect);
    }

    @Test
    public void getTagSet_validStrings_success() {
        Set<Tag> tagSet = SampleDataUtil.getTagSet("friends", "colleagues");
        assertNotNull(tagSet);
        assertEquals(2, tagSet.size());
    }

    @Test
    public void getProducts_validStrings_success() {
        Products products = SampleDataUtil.getProducts("milk", "bread", "eggs");
        assertNotNull(products);
    }

    @Test
    public void getSkills_validStrings_success() {
        Skills skills = SampleDataUtil.getSkills("Java", "Python", "C++");
        assertNotNull(skills);
    }
}
