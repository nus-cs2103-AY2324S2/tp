package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_GROUP_PERSON_INVALID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;
import seedu.address.model.person.Tag;

/**
 * assigns a group to an existing person in the address book.
 */
public class GroupCommand extends Command {
    public static final String MESSAGE_GROUP_PERSON_SUCCESS = "Grouped Person: %1$s";

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": assigns the person identified a group\n"
            + "Parameters:  "
            + "[" + PREFIX_NUSID + "NUSID] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + PREFIX_NUSID + "EXXXXXXX "
            + PREFIX_GROUP + "Class T15 "
            + PREFIX_TAG + "TA";

    private final Set<NusId> toGroup;
    private final GroupPersonDescriptor groupPersonDescriptor;

    /**
     * @param nusIds of the person in the filtered person list to group
     * @param groupPersonDescriptor details to group the person with
     */
    public GroupCommand(Set<NusId> nusIds, GroupPersonDescriptor groupPersonDescriptor) {
        requireNonNull(nusIds);
        toGroup = nusIds;
        this.groupPersonDescriptor = new GroupPersonDescriptor(groupPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        List<Person> personToGroup = lastShownList.stream()
                .filter(person -> toGroup.contains(person.getNusId()))
                .collect(Collectors.toList());
        List<Person> groupedPerson = createGroupedPerson(personToGroup, groupPersonDescriptor);

        if (personToGroup.isEmpty() || personToGroup.size() != toGroup.size()) {
            throw new CommandException(MESSAGE_GROUP_PERSON_INVALID);
        }

        IntStream.range(0, personToGroup.size())
                .forEach(index -> {
                    Person original = personToGroup.get(index);
                    Person modified = groupedPerson.get(index);
                    model.setPerson(original, modified);
                });

        return new CommandResult(String.format(MESSAGE_GROUP_PERSON_SUCCESS, Messages.format(groupedPerson)));
    }

    /**
     * Modify {@code personsToGroup} according to {@code groupPersonDescriptor}
     *
     * @param personsToGroup List of Person to be modified
     * @param groupPersonDescriptor Values to be modified to
     * @return Modified list of Person
     */
    public static List<Person> createGroupedPerson(List<Person> personsToGroup,
                                                   GroupPersonDescriptor groupPersonDescriptor) {
        List<Person> groupedPersons = new ArrayList<>();

        for (Person personToGroup : personsToGroup) {
            assert personToGroup != null;

            NusId nusId = personToGroup.getNusId();
            Name name = personToGroup.getName();
            Phone phone = personToGroup.getPhone();
            Email email = personToGroup.getEmail();
            Tag updatedTag = groupPersonDescriptor.getTag().orElse(personToGroup.getTag());
            Set<Group> updatedGroups = groupPersonDescriptor.getGroups().orElse(personToGroup.getGroups());
            Schedule schedule = personToGroup.getSchedule();
            Remark remark = personToGroup.getRemark();

            groupedPersons.add(new Person(nusId, name, phone, email, updatedTag, updatedGroups, schedule, remark));
        }

        return groupedPersons;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nusIds", toGroup)
                .add("groupPersonDescriptor", groupPersonDescriptor)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GroupCommand)) {
            return false;
        }

        GroupCommand otherGroupCommand = (GroupCommand) other;
        return toGroup.size() == otherGroupCommand.toGroup.size()
                && toGroup.containsAll(otherGroupCommand.toGroup)
                && groupPersonDescriptor.equals(otherGroupCommand.groupPersonDescriptor);
    }

    /**
     * Sets {@code group} to this object's {@code groups}.
     * A defensive copy of {@code groups} is used internally.
     */
    public static class GroupPersonDescriptor {
        private Set<Group> groups;
        private Tag tag;

        public GroupPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public GroupPersonDescriptor(GroupPersonDescriptor toCopy) {
            setGroups(toCopy.groups);
            setTag(toCopy.tag);
        }

        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setGroups(Set<Group> groups) {
            this.groups = (groups != null) ? new HashSet<>(groups) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Group>> getGroups() {
            return (groups != null) ? Optional.of(Collections.unmodifiableSet(groups)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof GroupCommand.GroupPersonDescriptor)) {
                return false;
            }

            GroupPersonDescriptor otherGroupPersonDescriptor = (GroupPersonDescriptor) other;
            return Objects.equals(groups, otherGroupPersonDescriptor.groups)
                    && Objects.equals(tag, otherGroupPersonDescriptor.tag);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("groups", groups)
                    .add("tag", tag)
                    .toString();
        }
    }
}


