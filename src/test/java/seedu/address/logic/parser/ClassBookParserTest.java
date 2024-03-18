package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CreateClassCommand;
import seedu.address.model.person.Classes;
import seedu.address.testutil.ClassBuilder;
import seedu.address.testutil.PersonUtil;
public class ClassBookParserTest {

    private final ClassBookParser parser = new ClassBookParser();

    @Test
    public void parseCommand_createClass() throws Exception {
        Classes classes = new ClassBuilder().build();
        CreateClassCommand command = (CreateClassCommand) parser.parseCommand(PersonUtil.getClassCommand(classes));
        assertEquals(new CreateClassCommand(classes), command);
    }

}
