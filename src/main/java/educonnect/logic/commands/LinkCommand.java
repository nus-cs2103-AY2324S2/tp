package educonnect.logic.commands;

import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_LINK;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import educonnect.commons.util.CollectionUtil;
import educonnect.commons.util.ToStringBuilder;
import educonnect.logic.Messages;
import educonnect.logic.commands.exceptions.CommandException;
import educonnect.model.Model;
import educonnect.model.student.Email;
import educonnect.model.student.Link;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;

/**
 * Links a website to a student identified using their unique student ID, email, or telegram handle.
 */
public class LinkCommand extends Command {

    public static final String COMMAND_WORD = "link";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds or removes a weblink to the student's page identified "
            + "only by the unique identifier used in the displayed student list.\n"
            + "Parameters: "
            + "[" + PREFIX_STUDENT_ID + "STUDENT_ID] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_TELEGRAM_HANDLE + "TELEGRAM_HANDLE] "
            + PREFIX_LINK + "WEBLINK\n\n"
            + "Example 1: " + COMMAND_WORD + " "
            + PREFIX_EMAIL + "example@email.com "
            + PREFIX_LINK + "https://www.google.com/\n\n"
            + "Example 2: " + COMMAND_WORD + " "
            + PREFIX_STUDENT_ID + "A1234567X "
            + PREFIX_LINK + "https://www.google.com/\n\n"
            + "Example 3: " + COMMAND_WORD + " "
            + PREFIX_TELEGRAM_HANDLE + "@john.doe "
            + PREFIX_LINK + "https://www.google.com/\n";

    public static final String MESSAGE_LINK_STUDENT_SUCCESS = "Linked Student to a weblink: %1$s";
    public static final String MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE =
            "Multiple unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String NON_UNIQUE_IDENTIFIER_MESSAGE =
            "Non-unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String NO_UNIQUE_IDENTIFIER_MESSAGE =
            "Use at least one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String NO_LINK_IDENTIFIER_MESSAGE =
            "No link prefixes used, please use " + PREFIX_LINK + " before your link.\n";

    private final LinkStudentDescriptor linkStudentDescriptor;

    public LinkCommand(LinkStudentDescriptor linkStudentDescriptor) {
        this.linkStudentDescriptor = linkStudentDescriptor;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LinkCommand)) {
            return false;
        }

        LinkCommand otherLinkCommand = (LinkCommand) other;
        return linkStudentDescriptor.equals(otherLinkCommand.linkStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("linkStudentDescriptor", linkStudentDescriptor)
                .toString();
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
            return new CommandResult(String.format(MESSAGE_LINK_STUDENT_SUCCESS, Messages.format(newStudent)));
        }).orElseThrow(() -> new CommandException(Messages.MESSAGE_NO_STUDENT_FOUND));
    }

    /**
     * Stores the details to link the student with. The original
     */
    public static class LinkStudentDescriptor {
        private StudentId studentId;
        private Email email;
        private TelegramHandle telegramHandle;
        private Link link;

        public LinkStudentDescriptor() {}

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

        public void setLink(Link link) {
            this.link = link;
        }

        public Optional<Link> getLinks() {
            return Optional.ofNullable(link);
        }

        public int countUniqueIdentifiers() {
            return CollectionUtil.countNonNull(studentId, telegramHandle, email);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof LinkStudentDescriptor)) {
                return false;
            }

            LinkStudentDescriptor otherLinkStudentDescriptor = (LinkStudentDescriptor) other;
            return Objects.equals(studentId, otherLinkStudentDescriptor.studentId)
                    && Objects.equals(email, otherLinkStudentDescriptor.email)
                    && Objects.equals(telegramHandle, otherLinkStudentDescriptor.telegramHandle)
                    && Objects.equals(link, otherLinkStudentDescriptor.link);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("student id", studentId)
                    .add("email", email)
                    .add("telegram handle", telegramHandle)
                    .add("link", link)
                    .toString();
        }
    }
    /**
     * Creates a new {@code Student} object with edited information based on the given {@code studentToEdit}
     * and {@code link}. All the information of the edited student stays the same except for the link.
     *
     * @param studentToEdit The original {@code Student} object to be edited. Must not be null.
     * @param linkStudentDescriptor The {@code LinkStudentDescriptor} containing updated link information.
     * @return A new {@code Student} object with edited information.
     * @throws IllegalArgumentException if {@code studentToEdit} is null.
     */
    public static Student createEditedStudent(Student studentToEdit, LinkStudentDescriptor linkStudentDescriptor) {
        assert studentToEdit != null;

        return new Student(studentToEdit.getName(),
                studentToEdit.getStudentId(),
                studentToEdit.getEmail(),
                studentToEdit.getTelegramHandle(),
                linkStudentDescriptor.getLinks(),
                studentToEdit.getTags(),
                studentToEdit.getTimetable());
    }
}
