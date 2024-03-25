package seedu.address.model.startup;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStartups.ALICE;
import static seedu.address.testutil.TypicalStartups.BOB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.startup.exceptions.DuplicateStartupException;
import seedu.address.model.startup.exceptions.StartupNotFoundException;
import seedu.address.testutil.StartupBuilder;

public class UniqueStartupListTest {

    private final UniqueStartupList uniqueStartupList = new UniqueStartupList();

    @Test
    public void contains_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.contains(null));
    }

    @Test
    public void contains_startupNotInList_returnsFalse() {
        assertFalse(uniqueStartupList.contains(ALICE));
    }

    @Test
    public void contains_startupInList_returnsTrue() {
        uniqueStartupList.add(ALICE);
        assertTrue(uniqueStartupList.contains(ALICE));
    }

    @Test
    public void contains_startupWithSameIdentityFieldsInList_returnsTrue() {
        uniqueStartupList.add(ALICE);
        Startup editedAlice = new StartupBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(uniqueStartupList.contains(editedAlice));
    }

    @Test
    public void add_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.add(null));
    }

    @Test
    public void add_duplicateStartup_throwsDuplicateStartupException() {
        uniqueStartupList.add(ALICE);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.add(ALICE));
    }

    @Test
    public void setStartup_nullTargetStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartup(null, ALICE));
    }

    @Test
    public void setStartup_nullEditedStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartup(ALICE, null));
    }

    @Test
    public void setStartup_targetStartupNotInList_throwsStartupNotFoundException() {
        assertThrows(StartupNotFoundException.class, () -> uniqueStartupList.setStartup(ALICE, ALICE));
    }

    @Test
    public void setStartup_editedStartupIsSameStartup_success() {
        uniqueStartupList.add(ALICE);
        uniqueStartupList.setStartup(ALICE, ALICE);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(ALICE);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasSameIdentity_success() {
        uniqueStartupList.add(ALICE);
        Startup editedAlice = new StartupBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        uniqueStartupList.setStartup(ALICE, editedAlice);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(editedAlice);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasDifferentIdentity_success() {
        uniqueStartupList.add(ALICE);
        uniqueStartupList.setStartup(ALICE, BOB);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(BOB);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartup_editedStartupHasNonUniqueIdentity_throwsDuplicateStartupException() {
        uniqueStartupList.add(ALICE);
        uniqueStartupList.add(BOB);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.setStartup(ALICE, BOB));
    }

    @Test
    public void remove_nullStartup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.remove(null));
    }

    @Test
    public void remove_startupDoesNotExist_throwsStartupNotFoundException() {
        assertThrows(StartupNotFoundException.class, () -> uniqueStartupList.remove(ALICE));
    }

    @Test
    public void remove_existingStartup_removesStartup() {
        uniqueStartupList.add(ALICE);
        uniqueStartupList.remove(ALICE);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_nullUniqueStartupList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartups((UniqueStartupList) null));
    }

    @Test
    public void setStartups_uniqueStartupList_replacesOwnListWithProvidedUniqueStartupList() {
        uniqueStartupList.add(ALICE);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(BOB);
        uniqueStartupList.setStartups(expectedUniqueStartupList);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueStartupList.setStartups((List<Startup>) null));
    }

    @Test
    public void setStartups_list_replacesOwnListWithProvidedList() {
        uniqueStartupList.add(ALICE);
        List<Startup> startupList = Collections.singletonList(BOB);
        uniqueStartupList.setStartups(startupList);
        UniqueStartupList expectedUniqueStartupList = new UniqueStartupList();
        expectedUniqueStartupList.add(BOB);
        assertEquals(expectedUniqueStartupList, uniqueStartupList);
    }

    @Test
    public void setStartups_listWithDuplicateStartups_throwsDuplicateStartupException() {
        List<Startup> listWithDuplicateStartups = Arrays.asList(ALICE, ALICE);
        assertThrows(DuplicateStartupException.class, () -> uniqueStartupList.setStartups(listWithDuplicateStartups));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueStartupList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueStartupList.asUnmodifiableObservableList().toString(), uniqueStartupList.toString());
    }
}
