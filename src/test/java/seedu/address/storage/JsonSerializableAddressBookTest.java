package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.AddressBook;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.TypicalPersons;

public class JsonSerializableAddressBookTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableAddressBookTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableAddressBook dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
            JsonSerializableAddressBook.class).get();
        AddressBook addressBookFromFile = dataFromFile.toModelType();
        AddressBook typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        List<JsonAdaptedPerson> validPersons = new ArrayList<>();
        validPersons.add(new JsonAdaptedPerson(TypicalPersons.ALICE));
        validPersons.add(new JsonAdaptedPerson(TypicalPersons.ALICE)); // Duplicate person
        JsonSerializableAddressBook data = new JsonSerializableAddressBook(validPersons, new ArrayList<>());
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook
            .MESSAGE_DUPLICATE_PERSON, data::toModelType);
    }

    @Test
    public void toModelType_duplicateModules_throwsIllegalValueException() throws Exception {
        List<JsonAdaptedModule> validModules = new ArrayList<>();
        validModules.add(new JsonAdaptedModule(new ModuleCode("CS1010")));
        validModules.add(new JsonAdaptedModule(new ModuleCode("CS1010"))); // Duplicate module
        JsonSerializableAddressBook data = new JsonSerializableAddressBook(new ArrayList<>(), validModules);
        assertThrows(IllegalValueException.class, JsonSerializableAddressBook
            .MESSAGE_DUPLICATE_MODULE, data::toModelType);
    }
}

