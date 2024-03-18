package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;
import seedu.address.model.tag.exceptions.TagNotFoundException;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(new Tag("friends"));
        assertTrue(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void contains_tagWithSameNameInList_returnsTrue() {
        uniqueTagList.add(new Tag("friends"));
        assertTrue(uniqueTagList.contains(new Tag("friends")));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(new Tag("friends"));
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(new Tag("friends")));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(new Tag("friends")));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(new Tag("friends"));
        uniqueTagList.remove(new Tag("friends"));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(new Tag("friends"));
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(new Tag("colleagues"));
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((Set<Tag>) null));
    }

    @Test
    public void setTags_set_replacesOwnListWithProvidedSet() {
        uniqueTagList.add(new Tag("friends"));
        Set<Tag> tagSet = new HashSet<>(Arrays.asList(new Tag("colleagues"), new Tag("family")));
        uniqueTagList.setTags(tagSet);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(new Tag("colleagues"));
        expectedUniqueTagList.add(new Tag("family"));
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void asUnmodifiableObservableSet_modifySet_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTagList.asUnmodifiableObservableSet().remove(new Tag("friends")));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueTagList.asUnmodifiableObservableSet().toString(), uniqueTagList.toString());
    }
}

