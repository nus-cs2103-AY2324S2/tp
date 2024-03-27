package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREETIMETAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.RoomNumber;
import seedu.address.model.person.Telegram;
import seedu.address.model.tag.FreeTimeTag;

/**
 * Adds a free time to an existing person in the address book.
 */
public class AddTimeCommand extends Command {

    public static final String COMMAND_WORD = "addTime";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a free time to the person identified "
            + "by the index number used in the displayed person list. "
            + PREFIX_FREETIMETAG + "Mon:1300-1400";

    public static final String MESSAGE_ADD_FREETIME_SUCCESS = "Added free time to person: %1$s";
    public static final String MORE_THAN_ONE_FREETIME = "Only one free time can be added a time.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public AddTimeCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPerson(personToEdit, editPersonDescriptor);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_ADD_FREETIME_SUCCESS, Messages.format(editedPerson)));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person createEditedPerson(Person personToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        FreeTimeTag freeTimeTag = editPersonDescriptor.getTags().toArray(new FreeTimeTag[1])[0];

        Set<FreeTimeTag> freeTimeTags = personToEdit.getTags();

        Set<FreeTimeTag> updatedTags = new HashSet<>();

        boolean added = false;

        if (freeTimeTags.size() == 0) {
            updatedTags.add(freeTimeTag);
            added = true;
        } else {
            String trimmedFreeTimeTag = freeTimeTags.toString().substring(2, freeTimeTags.toString().length() - 2);
            String day = trimmedFreeTimeTag.substring(0, 3);
            Integer newStart = Integer.parseInt(trimmedFreeTimeTag.substring(4, 8));
            Integer newEnd = Integer.parseInt(trimmedFreeTimeTag.substring(9, 13));

            for (FreeTimeTag tag : freeTimeTags) {
                String trimmedTag = tag.toString().substring(1, tag.toString().length() - 1);
                if (!trimmedTag.substring(0, 3).equals(day)) {
                    updatedTags.add(tag);
                } else {
                    Integer currentStart = Integer.parseInt(trimmedTag.substring(4, 8));
                    Integer currentEnd = Integer.parseInt(trimmedTag.substring(9, 13));

                    if (currentStart > newStart) {
                        updatedTags.add(tag);
                    } else if (currentStart.equals(newStart)) {
                        updatedTags.add(tag);
                        if (!added) {
                            updatedTags.add(freeTimeTag);
                            added = true;
                        }
                    } else {
                        updatedTags.add(tag);
                        if (!added) {
                            updatedTags.add(freeTimeTag);
                            added = true;
                        }
                    }
                }
            }
        }

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        RoomNumber updatedRoomNumber = personToEdit.getRoomNumber();
        Telegram updatedTelegram = personToEdit.getTelegram();
        Birthday updatedBirthday = personToEdit.getBirthday();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedRoomNumber, updatedTelegram,
                updatedBirthday, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTimeCommand)) {
            return false;
        }

        AddTimeCommand otherAddTimeCommand = (AddTimeCommand) other;
        return index.equals(otherAddTimeCommand.index)
                && editPersonDescriptor.equals(otherAddTimeCommand.editPersonDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPersonDescriptor", editPersonDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Set<FreeTimeTag> tags;
        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<FreeTimeTag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Set<FreeTimeTag> getTags() {
            return tags;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            EditPersonDescriptor otherEditPersonDescriptor = (EditPersonDescriptor) other;
            return Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("freeTimeTags", tags)
                    .toString();
        }
    }
}
