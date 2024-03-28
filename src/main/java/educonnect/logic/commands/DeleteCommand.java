package educonnect.logic.commands;

import static educonnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static educonnect.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static educonnect.logic.parser.CliSyntax.PREFIX_TELEGRAM_HANDLE;
import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Optional;

import educonnect.commons.util.ToStringBuilder;
import educonnect.logic.Messages;
import educonnect.logic.commands.exceptions.CommandException;
import educonnect.model.Model;
import educonnect.model.student.Email;
import educonnect.model.student.Student;
import educonnect.model.student.StudentId;
import educonnect.model.student.TelegramHandle;

/**
 * Deletes a student identified using it's displayed index from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified only by the unique identifier used in the displayed student list.\n"
            + "Parameters: " + PREFIX_EMAIL + "EMAIL or "
            + PREFIX_TELEGRAM_HANDLE + "TELEGRAM_HANDLE or "
            + PREFIX_STUDENT_ID + "STUDENT_ID\n"
            + "Example 1: " + COMMAND_WORD + " " + PREFIX_EMAIL + "example@email.com\n"
            + "Example 2: " + COMMAND_WORD + " " + PREFIX_STUDENT_ID + "A1234567X\n"
            + "Example 3: " + COMMAND_WORD + " " + PREFIX_TELEGRAM_HANDLE + "@john.doe";

    public static final String MULTIPLE_UNIQUE_IDENTIFIER_MESSAGE =
            "Multiple unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String NO_UNIQUE_IDENTIFIER_MESSAGE =
            "Non-unique identifier prefixes used, only use one unique identifier prefix.\n" + MESSAGE_USAGE;
    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final DeleteStudentDescriptor deleteStudentDescriptor;

    public DeleteCommand(DeleteStudentDescriptor deleteStudentDescriptor) {
        this.deleteStudentDescriptor = deleteStudentDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Student> optionalToBeDeleted = Optional.empty();

        if (deleteStudentDescriptor.getStudentId().isPresent()) {
            optionalToBeDeleted = deleteStudentDescriptor.getStudentId().flatMap(
                    studentId -> model.getStudentWithStudentId(studentId));

        } else if (deleteStudentDescriptor.getEmail().isPresent()) {
            optionalToBeDeleted = deleteStudentDescriptor.getEmail().flatMap(
                    email -> model.getStudentWithEmail(email));

        } else if (deleteStudentDescriptor.getTelegramHandle().isPresent()) {
            optionalToBeDeleted = deleteStudentDescriptor.getTelegramHandle().flatMap(
                    tele -> model.getStudentWithTelegramHandle(tele));
        }

        // No student exists in the book
        if (optionalToBeDeleted.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_STUDENT_FOUND);
        }

        Student studentToDelete = optionalToBeDeleted.get();

        model.deleteStudent(studentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, Messages.format(studentToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return deleteStudentDescriptor.equals(otherDeleteCommand.deleteStudentDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("deleteStudentDescriptor", deleteStudentDescriptor)
                .toString();
    }

    /**
     * Stores the details of to delete student with. Each non-empty field value will replace the
     * corresponding field value of the student
     */
    public static class DeleteStudentDescriptor {
        private StudentId studentId;
        private Email email;
        private TelegramHandle telegramHandle;

        public DeleteStudentDescriptor() {}

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

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteCommand.DeleteStudentDescriptor)) {
                return false;
            }

            DeleteCommand.DeleteStudentDescriptor otherDeleteStudentDescriptor =
                    (DeleteCommand.DeleteStudentDescriptor) other;

            return Objects.equals(studentId, otherDeleteStudentDescriptor.studentId)
                    && Objects.equals(email, otherDeleteStudentDescriptor.email)
                    && Objects.equals(telegramHandle, otherDeleteStudentDescriptor.telegramHandle);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("student id", studentId)
                    .add("email", email)
                    .add("telegram handle", telegramHandle)
                    .toString();
        }
    }

}
