package seedu.address.logic.relationship;

import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import seedu.address.model.person.relationship.Relationship;


/**
 * Represents a command to delete a relationship between two persons.
 */
public class DeleteRelationshipCommand extends Command {
    public static final String COMMAND_WORD = "deleteRelation";
    private static String MESSAGE_SUCCESS = "delete successful";
    private String originUuid;
    private String targetUuid;
    private String relationshipDescriptor;

    /**
     * Constructor for deleteRelationshipCommand, deletes Relationship sepcified by the 2 Person Uuid given if the
     * relationship exist otherwise tell user relationship do not exist
     * @param originUuid First Person UUID of relationship
     * @param targetUuid Second Person UUID of relationship
     * @param relationshipDescriptor String describing the type of relationship if exisiting
     */
    public DeleteRelationshipCommand(String originUuid, String targetUuid, String relationshipDescriptor) {
        this.originUuid = originUuid;
        this.targetUuid = targetUuid;
        this.relationshipDescriptor = relationshipDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        UUID fullOriginUuid = model.getFullUuid(originUuid);
        UUID fullTargetUuid = model.getFullUuid(targetUuid);
        if (fullOriginUuid == null || fullTargetUUID == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
        if (fullOriginUuid == fullTargetUUID) {
            throw new CommandException("Relationships must be between 2 different people");
        }
        try {
            Relationship toDelete = new Relationship(fullOriginUuid, fullTargetUUID, relationshipDescriptor);
            if (!model.hasRelationship(toDelete)) {
                throw new CommandException(String.format("Sorry %s do not exist", toDelete));
            }
            model.deleteRelationship(toDelete);
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (IllegalArgumentException e) {
            throw new CommandException(String.format("Sorry, we do not accept %s as type", relationshipDescriptor));
        }
    }
}


