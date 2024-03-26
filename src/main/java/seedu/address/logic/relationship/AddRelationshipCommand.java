package seedu.address.logic.relationship;

import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.relationship.BioParentsRelationship;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RoleBasedRelationship;
import seedu.address.model.person.relationship.SiblingRelationship;
import seedu.address.model.person.relationship.SpousesRelationship;

/**
 * This class is responsible for parsing and executing commands to add relationships between persons.
 * It supports adding relationships with and without roles.
 */
public class AddRelationshipCommand extends Command {
    public static final String COMMAND_WORD = "addRelation";
    public static final String MESSAGE_ADD_RELATIONSHIP_SUCCESS = "Add success";
    private String originUuid;
    private String targetUuid;
    private String relationshipDescriptor;

    private String rolePerson1;
    private String rolePerson2;
    /**
     * Constructor takes in the string arguments needed to be passed into the relationship constructor and performs
     * the addition of the relationship
     * @param originUuid
     * @param targetUuid
     * @param relationshipDescriptor
     */
    public AddRelationshipCommand(String originUuid, String targetUuid, String relationshipDescriptor) {
        this.originUuid = originUuid;
        this.targetUuid = targetUuid;
        this.relationshipDescriptor = relationshipDescriptor.toLowerCase();
    }

    /**
     * Constructor takes in the string arguments needed to be passed into the relationship constructor and performs
     * the addition of the relationship
     * @param originUuid
     * @param targetUuid
     * @param relationshipDescriptor
     * @param role1
     * @param role2
     */
    public AddRelationshipCommand(String originUuid, String targetUuid, String relationshipDescriptor,
                                  String role1, String role2) {
        this.originUuid = originUuid;
        this.targetUuid = targetUuid;
        this.relationshipDescriptor = relationshipDescriptor.toLowerCase();
        this.rolePerson1 = role1.toLowerCase();
        this.rolePerson2 = role2.toLowerCase();
    }

    /**
     * Executes the command to add a relationship between two persons
     * @param model
     * @return CommandResult
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        boolean isRoleBased = (rolePerson1 != null) && (rolePerson2 != null);
        UUID fullOriginUuid = model.getFullUuid(originUuid);
        UUID fullTargetUuid = model.getFullUuid(targetUuid);
        if (fullTargetUuid == null || fullOriginUuid == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
        if (fullOriginUuid == fullTargetUuid) {
            throw new CommandException("Relationships must be between 2 different people");
        }
        try {
            if (isRoleBased) {
                RoleBasedRelationship toAdd;
                if (relationshipDescriptor.equalsIgnoreCase("Bioparents")) {
                    toAdd = new BioParentsRelationship(fullOriginUuid, fullTargetUuid);
                } else if (relationshipDescriptor.equalsIgnoreCase("Siblings")) {
                    toAdd = new SiblingRelationship(fullOriginUuid, fullTargetUuid);
                } else if (relationshipDescriptor.equalsIgnoreCase("Spouses")) {
                    toAdd = new SpousesRelationship(fullOriginUuid, fullTargetUuid);
                } else {
                    toAdd = new RoleBasedRelationship(fullOriginUuid, fullTargetUuid,
                            relationshipDescriptor, rolePerson1, rolePerson2);
                }
                if (model.hasRelationshipWithDescriptor(toAdd)) {
                    String existing = model.getExistingRelationship(toAdd);
                    throw new CommandException(String.format("Sorry, %s", existing));
                }
                model.addRelationship(toAdd);
                return new CommandResult(MESSAGE_ADD_RELATIONSHIP_SUCCESS);
            }
            Relationship toAdd = new Relationship(fullOriginUuid, fullTargetUuid, relationshipDescriptor);
            if (model.hasRelationshipWithDescriptor(toAdd)) {
                String existing = model.getExistingRelationship(toAdd);
                throw new CommandException(String.format("Sorry, %s", existing));
            }
            model.addRelationship(toAdd);
            return new CommandResult(MESSAGE_ADD_RELATIONSHIP_SUCCESS);
        } catch (IllegalArgumentException e) {
            throw new CommandException(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddRelationshipCommand)) {
            return false;
        }
        AddRelationshipCommand other = (AddRelationshipCommand) o;
        return other.originUuid.equals(this.originUuid) && other.targetUuid.equals(targetUuid)
                && other.relationshipDescriptor.equals(this.relationshipDescriptor);
    }
}
