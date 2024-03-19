package seedu.address.logic.relationship;

import java.util.Map;
import java.util.UUID;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.relationship.BioParentsRelationship;
import seedu.address.model.person.relationship.FriendsRelationship;
import seedu.address.model.person.relationship.Relationship;
import seedu.address.model.person.relationship.RelationshipUtilManager;
import seedu.address.model.person.relationship.SpousesRelationship;

/**
 * This class is responsible for parsing and executing commands to add relationships between persons.
 * It supports adding relationships with and without roles.
 */
public class AddRelationshipCommand extends Command {
    private static String MESSAGE_SUCCESS = "";
    private String originUUID;
    private String targetUUID;
    private String relationshipDescriptor;

    public AddRelationshipCommand(String originUUID, String targetUUID, String relationshipDescriptor) {
        this.originUUID = originUUID;
        this.targetUUID = targetUUID;
        this.relationshipDescriptor = relationshipDescriptor;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        UUID fullOriginUUID = model.getIdFromString(originUUID);
        UUID fullTargetUUID = model.getIdFromString(targetUUID);
        Relationship toAdd = new Relationship(fullOriginUUID, fullTargetUUID, relationshipDescriptor);
        if (model.hasRelationship(toAdd)) {
            String existing = model.getExistingRelationship(toAdd);
            throw new CommandException(String.format("Sorry, %s", existing));
        }
        model.addRelationship(toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
     }

}
