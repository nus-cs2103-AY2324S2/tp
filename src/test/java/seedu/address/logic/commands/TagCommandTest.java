package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Id;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class TagCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Id firstStudentId = new Id("A1029384A");
        HashSet<Tag> firstTagList = new HashSet<Tag>();
        firstTagList.add(new Tag("TopStudent"));
        firstTagList.add(new Tag("Potential TA"));

        Id secondStudentId = new Id("A1029384A");
        HashSet<Tag> secondTagList = new HashSet<Tag>();
        secondTagList.add(new Tag("Potential TA"));

        Id thirdStudentId = new Id("A2129334F");
        HashSet<Tag> thirdTagList = new HashSet<Tag>();
        thirdTagList.add(new Tag("BadStudent"));
        thirdTagList.add(new Tag("GoodStudent"));

        TagCommand firstTagCommand = new TagCommand(firstStudentId, firstTagList);
        TagCommand secondTagCommand = new TagCommand(secondStudentId, secondTagList);
        TagCommand thirdTagCommand = new TagCommand(thirdStudentId, thirdTagList);

        // same object -> returns true
        assertTrue(firstTagCommand.equals(firstTagCommand));

        // same values -> returns true
        assertTrue(firstTagCommand.equals(secondTagCommand));

        // different types -> returns false
        assertFalse(firstTagCommand.equals(1));

        // null -> returns false
        assertFalse(firstTagCommand.equals(null));

        // different person -> returns false
        assertFalse(firstTagCommand.equals(thirdTagCommand));
    }

    @Test
    public void execute_noStudentFound_throwCommandException() {
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("TopStudent"));
        Id invalidId = new Id("A0912124E");
        TagCommand tagCommand = new TagCommand(invalidId, tagList);

        assertCommandFailure(tagCommand, model, TagCommand.MESSAGE_PERSON_NOTFOUND);
    }

    //workingon, make good use of the util provided instead of adding by your own.
    @Test
    public void execute_duplicateTag_success() {
        HashSet<Tag> tagList = new HashSet<Tag>();
        tagList.add(new Tag("TopStudent"));
        Id invalidId = new Id("A9124E");
        TagCommand tagCommand = new TagCommand(invalidId, tagList);

        assertCommandFailure(tagCommand, model, TagCommand.MESSAGE_PERSON_NOTFOUND);
    }

//    //workingon
//    @Test
//    public void execute_validId_validTag_success() {
//        HashSet<Tag> tagList = new HashSet<Tag>();
//        tagList.add(new Tag("TopStudent"));
//        Id invalidId = new Id("A0156724L");
//        TagCommand tagCommand = new TagCommand(invalidId, tagList);
//
//        Person personToTag = null;
//        for (Person person:model.getAddressBook().getPersonList()){
//            if (person.getId().equals(new Id("A0156724L"))){
//                personToTag = person;
//            }
//        }
//        Model expectedModel = new Mo
//
//
//    }
//
//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
//        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
//        FindCommand command = new FindCommand(predicate);
//        expectedModel.updateFilteredPersonList(predicate);
//        assertCommandSuccess(command, model, expectedMessage, expectedModel);
//        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
//    }
//
//    @Test
//    public void toStringMethod() {
//        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
//        FindCommand findCommand = new FindCommand(predicate);
//        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
//        assertEquals(expected, findCommand.toString());
//    }
//
//    /**
//     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
//     */
//    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
//        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
//    }
}
