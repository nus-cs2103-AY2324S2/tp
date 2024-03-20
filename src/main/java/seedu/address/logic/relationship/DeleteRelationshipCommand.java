package seedu.address.logic.relationship;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RelationshipUtil;

/**
 * Represents a command to delete a relationship between two persons.
 */
public class DeleteRelationshipCommand extends Command {
    public static final String COMMAND_WORD = "deleteRelation";
    private static String MESSAGE_SUCCESS = "Delete Successful";
    private String originUUID;
    private String targetUUID;
    private String relationshipDescriptor;

    public DeleteRelationshipCommand(String originUUID, String targetUUID, String relationshipDescriptor) {
        this.originUUID = originUUID;
        this.targetUUID = targetUUID;
        this.relationshipDescriptor = relationshipDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        UUID fullOriginUUID = model.getFullUUID(originUUID);
        UUID fullTargetUUID = model.getFullUUID(targetUUID);
        if (fullOriginUUID == null || fullTargetUUID == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
        if (fullOriginUUID == fullTargetUUID) {
            throw new CommandException("Relationships must be between 2 different people");
        }
        try {
            Relationship toDelete = new Relationship(fullOriginUUID, fullTargetUUID, relationshipDescriptor);
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


