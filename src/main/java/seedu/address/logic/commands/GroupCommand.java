package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_GROUP_PERSON;
import static seedu.address.logic.Messages.MESSAGE_GROUP_PERSON_INVALID;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Name;
import seedu.address.model.person.Email;
import seedu.address.model.person.Tag;




/**
 * assigns a group to an existing person in the address book.
 */
public class GroupCommand extends Command {

    public static final String COMMAND_WORD = "group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": assigns the person identified a group\n"
            + "Parameters:  "
            + "[" + PREFIX_NUSID + "NUSID] "
            + "[" + PREFIX_GROUP + "GROUP] "
            + "[" + PREFIX_TAG + "TAG] \n"
            + "Example: " + COMMAND_WORD + PREFIX_NUSID + "eXXXXXXX "
            + PREFIX_GROUP + "Class T15 "
            + PREFIX_TAG + "TA";

    private final NusId toGroup;
    private final GroupPersonDescriptor groupPersonDescriptor;




    /**
     * @param nusid of the person in the filtered person list to group
     * @param groupPersonDescriptor details to group the person with
     */
    public GroupCommand(NusId nusid, GroupPersonDescriptor groupPersonDescriptor) {
        requireNonNull(nusid);
        toGroup = nusid;
        this.groupPersonDescriptor = new GroupPersonDescriptor(groupPersonDescriptor);


    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToGroup = lastShownList.stream().filter(person -> person.getNusId().equals(toGroup))
                        .findFirst().orElse(null);

        //System.out.println(toGroup);
        if (personToGroup == null) {
            throw new CommandException(MESSAGE_GROUP_PERSON_INVALID);
        }
        Person groupedPerson = createGroupedPerson(personToGroup, groupPersonDescriptor);
        model.setPerson(personToGroup, groupedPerson);

        return new CommandResult(MESSAGE_GROUP_PERSON, true, false);
    }

    private static Person createGroupedPerson(Person personToGroup, GroupPersonDescriptor GroupPersonDescriptor) {
        assert personToGroup != null;

        NusId nusId = personToGroup.getNusId();
        Name name = personToGroup.getName();
        Phone phone = personToGroup.getPhone();
        Email email = personToGroup.getEmail();
        Tag updatedTag = GroupPersonDescriptor.getTag().orElse(personToGroup.getTag());
        Set<Group> updatedGroups = GroupPersonDescriptor.getGroups().orElse(personToGroup.getGroups());

        return new Person(nusId, name, phone, email, updatedTag, updatedGroups);
    }
    /**
     * Sets {@code group} to this object's {@code groups}.
     * A defensive copy of {@code groups} is used internally.
     */
    public static class GroupPersonDescriptor {

        private NusId nusid;
        private Set<Group> groups;
        private Tag tag;


        public GroupPersonDescriptor() {}
        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public GroupPersonDescriptor(GroupPersonDescriptor toCopy) {
            //setNusId(toCopy.nusid);
            this.nusid = toCopy.nusid;
            setGroups(toCopy.groups);
            setTag(toCopy.tag);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(groups, tag);
        }

        public void setNusId(NusId nusid) {
            this.nusid = nusid;
        }

        public Optional<NusId> getNusId() {
            return Optional.ofNullable(nusid);
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
            return Objects.equals(nusid, otherGroupPersonDescriptor.nusid)
                    && Objects.equals(groups, otherGroupPersonDescriptor.groups)
                    && Objects.equals(tag, otherGroupPersonDescriptor.tag);
                    //&& Objects.equals(address, otherEditPersonDescriptor.address)
                    //&& Objects.equals(tags, otherEditPersonDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("nusid", nusid)
                    .toString();
        }
    }
}


