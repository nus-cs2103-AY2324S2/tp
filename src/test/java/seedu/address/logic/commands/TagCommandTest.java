package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_singleTagAdd_success() {
        Set<Tag> tagStub = new HashSet<>();
        tagStub.add(new Tag("Finance"));

        Person expectedPerson = new PersonBuilder().withTags("Finance").build();

        TagCommand tagCommand = new TagCommand(new Id(PersonBuilder.DEFAULT_ID), tagStub);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        String expectedMessage = TagCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_multipleTagAdd_success() {
        Set<Tag> tagStub = new HashSet<>();
        tagStub.add(new Tag("Finance"));
        tagStub.add(new Tag("Management"));
        tagStub.add(new Tag("Friend"));

        Person expectedPerson = new PersonBuilder().withTags("Finance", "Management", "Friend").build();

        TagCommand tagCommand = new TagCommand(new Id(PersonBuilder.DEFAULT_ID), tagStub);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        String expectedMessage = TagCommand.MESSAGE_SUCCESS;

        assertCommandSuccess(tagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTags_throwsCommandException() {
        Set<Tag> tagStub = new HashSet<>();
        tagStub.add(new Tag("Finance"));
        tagStub.add(new Tag("Management"));

        Person expectedPerson = new PersonBuilder().withTags("Finance", "Management").build();
        TagCommand tagCommand = new TagCommand(new Id(PersonBuilder.DEFAULT_ID), tagStub);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), expectedPerson);

        String expectedMessage = TagCommand.MESSAGE_DUPLICATE_TAGS;

        assertThrows(CommandException.class, expectedMessage, () -> tagCommand.execute(expectedModel));
    }

    @Test
    public void execute_invalidId_throwsCommandException() {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        int size = expectedModel.getFilteredPersonList().size();
        Id invalidId = new Id(size + 240001);

        TagCommand tagCommand = new TagCommand(invalidId, new HashSet<Tag>());

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_ID;

        assertThrows(CommandException.class, expectedMessage, () -> tagCommand.execute(expectedModel));
    }

    @Test
    public void equals() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Finance"));
        tags.add(new Tag("Management"));
        TagCommand tagCommand = new TagCommand(new Id(240001), tags);

        Set<Tag> tagsCopy = new HashSet<>();
        tagsCopy.add(new Tag("Finance"));
        tagsCopy.add(new Tag("Management"));
        TagCommand tagCommandCopy = new TagCommand(new Id(240001), tagsCopy);

        // return true if same id and tags
        Assertions.assertTrue(tagCommand.equals(tagCommandCopy));

        // return true if same object
        Assertions.assertTrue(tagCommand.equals(tagCommand));

        // return false if null
        Assertions.assertFalse(tagCommand.equals(null));

        // return false if different command type
        Assertions.assertFalse(tagCommand.equals(new ClearCommand()));

        // return false if different id
        Assertions.assertFalse(tagCommand.equals(new TagCommand(new Id(240002), tags)));

        tagsCopy.add(new Tag("Family"));

        // return false if different tag names
        Assertions.assertFalse(tagCommand.equals(tagCommandCopy));
    }

    @Test
    public void toStringMethod() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("Finance"));
        TagCommand tagCommand = new TagCommand(new Id(240001), tags);

        String expected = TagCommand.class.getCanonicalName() + "{id=240001" + ", tags="
                + tags + "}";

        Assertions.assertEquals(expected, tagCommand.toString());

    }

}

