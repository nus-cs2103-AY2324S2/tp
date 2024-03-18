package educonnect.logic.parser;

import static educonnect.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static educonnect.logic.commands.CommandTestUtil.*;
import static educonnect.logic.parser.CliSyntax.*;


import educonnect.logic.Messages;
import educonnect.model.student.Email;
import educonnect.model.student.Link;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;
import org.junit.jupiter.api.Test;

import educonnect.logic.commands.LinkCommand;

public class LinkCommandParserTest {

    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_validArgsId_returnsLinkedCommand_success() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setStudentId(new StudentId(VALID_STUDENT_ID_JOHN));
        linkStudentDescriptor.setLink(new Link(VALID_LINK_JOHN));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_JOHN + " "
                + PREFIX_LINK + VALID_LINK_JOHN, new LinkCommand(linkStudentDescriptor));
    }
    @Test
    public void parse_validArgsEmail_returnsLinkedCommand_success() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setEmail(new Email(VALID_EMAIL_JOHN));
        linkStudentDescriptor.setLink(new Link(VALID_LINK_JOHN));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EMAIL + VALID_EMAIL_JOHN + " "
                + PREFIX_LINK + VALID_LINK_JOHN, new LinkCommand(linkStudentDescriptor));
    }

    @Test
    public void parse_validArgsTele_returnsLinkedCommand_success() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setTelegramHandle(new TelegramHandle(VALID_TELEGRAM_HANDLE_JOHN));
        linkStudentDescriptor.setLink(new Link(VALID_LINK_JOHN));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_TELEGRAM_HANDLE + VALID_TELEGRAM_HANDLE_JOHN + " "
                + PREFIX_LINK + VALID_LINK_JOHN, new LinkCommand(linkStudentDescriptor));
    }


    @Test
    public void parse_invalidLink_throwsParseException() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setStudentId(new StudentId(VALID_STUDENT_ID_JOHN));
        linkStudentDescriptor.setLink(new Link(INVALID_LINK));
        LinkCommand linkCommand = new LinkCommand(linkStudentDescriptor);

        CommandParserTestUtil.assertParseFailure(parser, " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_JOHN + " "
                + PREFIX_LINK + INVALID_LINK, Link.MESSAGE_CONSTRAINTS);
    }
}

