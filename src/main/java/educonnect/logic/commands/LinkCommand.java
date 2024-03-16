package educonnect.logic.commands;

import educonnect.commons.util.CollectionUtil;
import educonnect.commons.util.ToStringBuilder;
import educonnect.logic.Messages;
import educonnect.logic.commands.exceptions.CommandException;
import educonnect.model.Model;
import educonnect.model.student.*;
import educonnect.model.tag.Tag;

import java.util.*;

import static educonnect.logic.parser.CliSyntax.*;
import static java.util.Objects.requireNonNull;

public class LinkCommand extends Command{
    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a weblink to the student's page identified only by the unique identifier used in the displayed student list.\n"
            + "Parameters: " + PREFIX_EMAIL + "EMAIL or "
            + PREFIX_TELEGRAM_HANDLE + "TELEGRAM_HANDLE or "
            + PREFIX_STUDENT_ID + "STUDENT_ID\n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_EMAIL + "example@email.com " + "l/https://github.com/example/tp\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_STUDENT_ID + "A1234567X " + "l/https://github.com/example/tp\n"
            + "Example 3: " + COMMAND_WORD + " " + PREFIX_TELEGRAM_HANDLE + "@john.doe " + "l/https://github.com/example/tp\n";
    public static final String MESSAGE_LINK_STUDENT_SUCCESS = "Linked Student to a weblink: %1$s";
    public static final String MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE =
            "Multiple unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String NO_UNIQUE_IDENTIFIER_MESSAGE =
            "Non-unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;

    public static final String NO_LINK_IDENTIFIER_MESSAGE =
            "No link prefixes used, please use l/ before your link.\n";

    private final LinkStudentDescriptor linkStudentDescriptor;
    public LinkCommand(LinkStudentDescriptor linkStudentDescriptor) {
        this.linkStudentDescriptor = linkStudentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Student> toBeLinked = Optional.empty();
        if (linkStudentDescriptor.getStudentId().map(
                studentId -> model.hasStudentId(studentId)).isPresent()) {
            toBeLinked = linkStudentDescriptor.getStudentId().flatMap(
                    studentId -> model.getStudentWithStudentId(studentId));

        } else if (linkStudentDescriptor.getEmail().map(
                email -> model.hasEmail(email)).isPresent()) {

            toBeLinked = linkStudentDescriptor.getEmail().flatMap(
                    email -> model.getStudentWithEmail(email));

        } else if (linkStudentDescriptor.getTelegramHandle().map(
                telegramHandle -> model.hasTelegramHandle(telegramHandle)).isPresent()) {

            toBeLinked = linkStudentDescriptor.getTelegramHandle().flatMap(
                    tele -> model.getStudentWithTelegramHandle(tele));
        }

        return toBeLinked.map(studentToLink -> {
            Student newStudent = createEditedStudent(studentToLink, linkStudentDescriptor);
            model.setStudent(studentToLink, newStudent);
            model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
            System.out.println(newStudent);
            return new CommandResult(String.format(MESSAGE_LINK_STUDENT_SUCCESS, Messages.format(studentToLink)));
        }).orElseThrow(() -> new CommandException(Messages.MESSAGE_NO_STUDENT_FOUND));
    }
    public static class LinkStudentDescriptor {
        private Name name;
        private StudentId studentId;
        private Email email;
        private TelegramHandle telegramHandle;
        private Set<Tag> tags;
        private Link link;

        public LinkStudentDescriptor() {}

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, studentId, email, telegramHandle, tags);
        }



        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setTelegramHandle(TelegramHandle telegramHandle) {
            this.telegramHandle = telegramHandle;
        }

        public Optional<TelegramHandle> getTelegramHandle() {
            return Optional.ofNullable(telegramHandle);
        }

        public void setLink(Link link) { this.link = link; }

        public Optional<Link> getLinks() {
            return Optional.ofNullable(link);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof LinkCommand.LinkStudentDescriptor)) {
                return false;
            }

            LinkCommand.LinkStudentDescriptor otherLinkStudentDescriptor = (LinkCommand.LinkStudentDescriptor) other;
            return Objects.equals(name, otherLinkStudentDescriptor.name)
                    && Objects.equals(studentId, otherLinkStudentDescriptor.studentId)
                    && Objects.equals(email, otherLinkStudentDescriptor.email)
                    && Objects.equals(telegramHandle, otherLinkStudentDescriptor.telegramHandle)
                    && Objects.equals(tags, otherLinkStudentDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("student id", studentId)
                    .add("email", email)
                    .add("telegram handle", telegramHandle)
                    .add("tags", tags)
                    .add("link,", link)
                    .toString();
        }
    }

    private static Student createEditedStudent(Student studentToEdit, LinkStudentDescriptor link) {
        assert studentToEdit != null;

        Name updatedName = studentToEdit.getName();
        StudentId updatedStudentId = studentToEdit.getStudentId();
        Email updatedEmail = studentToEdit.getEmail();
        TelegramHandle updatedTelegramHandle = studentToEdit.getTelegramHandle();
        Link updatedLink = link.getLinks().orElse(studentToEdit.getLink());
        Set<Tag> updatedTags = studentToEdit.getTags();

        return new Student(updatedName, updatedStudentId, updatedEmail, updatedTelegramHandle, updatedLink, updatedTags);
    }
}
