package seedu.address.logic.relationship;

import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.relationship.Relationship;

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
        this.relationshipDescriptor = relationshipDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        UUID fullOriginUuid = model.getFullUuid(originUuid);
        UUID fullTargetUuid = model.getFullUuid(targetUuid);
        if (fullTargetUuid == null || fullOriginUuid == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
        if (fullOriginUuid == fullTargetUuid) {
            throw new CommandException("Relationships must be between 2 different people");
        }
        try {
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
}
