package educonnect.logic.parser;

import static educonnect.logic.commands.CommandTestUtil.INVALID_LINK;
import static educonnect.logic.commands.CommandTestUtil.VALID_EMAIL_JOHN;
import static educonnect.logic.commands.CommandTestUtil.VALID_LINK_JOHN;
import static educonnect.logic.commands.CommandTestUtil.VALID_STUDENT_ID_JOHN;
import static educonnect.logic.commands.CommandTestUtil.VALID_TELEGRAM_HANDLE_JOHN;
import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_LINK;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;

import org.junit.jupiter.api.Test;

import educonnect.logic.commands.LinkCommand;
import educonnect.model.student.Email;
import educonnect.model.student.Link;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;


public class LinkCommandParserTest {

    private LinkCommandParser parser = new LinkCommandParser();

    @Test
    public void parse_validArgsId_success() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setStudentId(new StudentId(VALID_STUDENT_ID_JOHN));
        linkStudentDescriptor.setLink(new Link(VALID_LINK_JOHN));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_STUDENT_ID + VALID_STUDENT_ID_JOHN + " "
                + PREFIX_LINK + VALID_LINK_JOHN, new LinkCommand(linkStudentDescriptor));
    }
    @Test
    public void parse_validArgsEmail_success() {
        LinkCommand.LinkStudentDescriptor linkStudentDescriptor = new LinkCommand.LinkStudentDescriptor();
        linkStudentDescriptor.setEmail(new Email(VALID_EMAIL_JOHN));
        linkStudentDescriptor.setLink(new Link(VALID_LINK_JOHN));

        CommandParserTestUtil.assertParseSuccess(parser, " " + PREFIX_EMAIL + VALID_EMAIL_JOHN + " "
                + PREFIX_LINK + VALID_LINK_JOHN, new LinkCommand(linkStudentDescriptor));
    }

    @Test
    public void parse_validArgsTelegram_success() {
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

