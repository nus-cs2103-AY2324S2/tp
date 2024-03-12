package seedu.address.model.coursemate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.ALICE;
import static seedu.address.testutil.TypicalCourseMates.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.coursemate.exceptions.CourseMateNotFoundException;
import seedu.address.model.coursemate.exceptions.DuplicateCourseMateException;
import seedu.address.testutil.CourseMateBuilder;

public class UniqueCourseMateListTest {

    private final UniqueCourseMateList uniqueCourseMateList = new UniqueCourseMateList();

    @Test
    public void contains_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.contains(null));
    }

    @Test
    public void contains_courseMateNotInList_returnsFalse() {
        assertFalse(uniqueCourseMateList.contains(ALICE));
    }

    @Test
    public void contains_courseMateInList_returnsTrue() {
        uniqueCourseMateList.add(ALICE);
        assertTrue(uniqueCourseMateList.contains(ALICE));
    }

    @Test
    public void contains_courseMateWithSameIdentityFieldsInList_returnsTrue() {
        uniqueCourseMateList.add(ALICE);
        CourseMate editedAlice = new CourseMateBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA).build();
        assertTrue(uniqueCourseMateList.contains(editedAlice));
    }

    @Test
    public void add_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.add(null));
    }

    @Test
    public void add_duplicateCourseMate_throwsDuplicateCourseMateException() {
        uniqueCourseMateList.add(ALICE);
        assertThrows(DuplicateCourseMateException.class, () -> uniqueCourseMateList.add(ALICE));
    }

    @Test
    public void setCourseMate_nullTargetCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.setCourseMate(null, ALICE));
    }

    @Test
    public void setCourseMate_nullEditedCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.setCourseMate(ALICE, null));
    }

    @Test
    public void setCourseMate_targetCourseMateNotInList_throwsCourseMateNotFoundException() {
        assertThrows(CourseMateNotFoundException.class, () -> uniqueCourseMateList.setCourseMate(ALICE, ALICE));
    }

    @Test
    public void setCourseMate_editedCourseMateIsSameCourseMate_success() {
        uniqueCourseMateList.add(ALICE);
        uniqueCourseMateList.setCourseMate(ALICE, ALICE);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        expectedUniqueCourseMateList.add(ALICE);
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMate_editedCourseMateHasSameIdentity_success() {
        uniqueCourseMateList.add(ALICE);
        CourseMate editedAlice = new CourseMateBuilder(ALICE)
                .withAddress(VALID_ADDRESS_BOB).withSkills(VALID_SKILL_JAVA).build();
        uniqueCourseMateList.setCourseMate(ALICE, editedAlice);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        expectedUniqueCourseMateList.add(editedAlice);
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMate_editedCourseMateHasDifferentIdentity_success() {
        uniqueCourseMateList.add(ALICE);
        uniqueCourseMateList.setCourseMate(ALICE, BOB);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        expectedUniqueCourseMateList.add(BOB);
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMate_editedCourseMateHasNonUniqueIdentity_throwsDuplicateCourseMateException() {
        uniqueCourseMateList.add(ALICE);
        uniqueCourseMateList.add(BOB);
        assertThrows(DuplicateCourseMateException.class, () -> uniqueCourseMateList.setCourseMate(ALICE, BOB));
    }

    @Test
    public void remove_nullCourseMate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.remove(null));
    }

    @Test
    public void remove_courseMateDoesNotExist_throwsCourseMateNotFoundException() {
        assertThrows(CourseMateNotFoundException.class, () -> uniqueCourseMateList.remove(ALICE));
    }

    @Test
    public void remove_existingCourseMate_removesCourseMate() {
        uniqueCourseMateList.add(ALICE);
        uniqueCourseMateList.remove(ALICE);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMates_nullUniqueCourseMateList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                uniqueCourseMateList.setCourseMates((UniqueCourseMateList) null));
    }

    @Test
    public void setCourseMates_uniqueCourseMateList_replacesOwnListWithProvidedUniqueCourseMateList() {
        uniqueCourseMateList.add(ALICE);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        expectedUniqueCourseMateList.add(BOB);
        uniqueCourseMateList.setCourseMates(expectedUniqueCourseMateList);
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMates_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueCourseMateList.setCourseMates((List<CourseMate>) null));
    }

    @Test
    public void setCourseMates_list_replacesOwnListWithProvidedList() {
        uniqueCourseMateList.add(ALICE);
        List<CourseMate> courseMateList = Collections.singletonList(BOB);
        uniqueCourseMateList.setCourseMates(courseMateList);
        UniqueCourseMateList expectedUniqueCourseMateList = new UniqueCourseMateList();
        expectedUniqueCourseMateList.add(BOB);
        assertEquals(expectedUniqueCourseMateList, uniqueCourseMateList);
    }

    @Test
    public void setCourseMates_listWithDuplicateCourseMates_throwsDuplicateCourseMateException() {
        List<CourseMate> listWithDuplicateCourseMates = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateCourseMateException.class, () ->
                uniqueCourseMateList.setCourseMates(listWithDuplicateCourseMates));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueCourseMateList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueCourseMateList.asUnmodifiableObservableList().toString(), uniqueCourseMateList.toString());
    }
}
