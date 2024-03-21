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
public class EditRelationshipCommand extends Command {
    public static final String COMMAND_WORD = "editRelation";
    public static final String MESSAGE_EDIT_RELATIONSHIP_SUCCESS = "Edit successful";
    private String originUuid;
    private String targetUuid;
    private String oldRelationshipDescriptor;
    private String newRelationshipDescriptor;

    /**
     * Constructor takes in the string arguments needed to be passed into the relationship constructor and performs
     * the addition of the relationship
     * @param originUuid
     * @param targetUuid
     * @param oldRelationshipDescriptor
     * @param newRelationshipDescriptor
     */
    public EditRelationshipCommand(String originUuid, String targetUuid,
                                   String oldRelationshipDescriptor, String newRelationshipDescriptor) {
        this.originUuid = originUuid;
        this.targetUuid = targetUuid;
        this.oldRelationshipDescriptor = oldRelationshipDescriptor;
        this.newRelationshipDescriptor = newRelationshipDescriptor;
    }

    /**
     * Executes the command to edit a relationship between two persons.
     * @param model {@code Model} which the command should operate on.
     * @return a CommandResult that contains the success message.
     * @throws CommandException if the relationship does not exist or if
     * the new relationship is the same as the old one.
     */
    public CommandResult execute(Model model) throws CommandException {
        UUID fullOriginUuid = model.getFullUuid(originUuid);
        UUID fullTargetUuid = model.getFullUuid(targetUuid);
        if (fullOriginUuid == null || fullTargetUuid == null) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_UUID);
        }
        if (fullOriginUuid == fullTargetUuid) {
            throw new CommandException("Relationships must be between 2 different people");
        }
        try {
            if (oldRelationshipDescriptor.equals(newRelationshipDescriptor)) {
                throw new CommandException("There's no need to edit the relationship "
                        + "if the new relationship is the same as the old one.");
            }
            Relationship toEditOff = new Relationship(fullOriginUuid, fullTargetUuid, oldRelationshipDescriptor);
            Relationship toEditIn = new Relationship(fullOriginUuid, fullTargetUuid, newRelationshipDescriptor);
            if (!model.hasRelationshipWithDescriptor(toEditOff)) {
                throw new CommandException(String.format("Sorry %s do not exist", toEditOff));
            }
            if (model.hasRelationshipWithDescriptor(toEditIn)) {
                throw new CommandException(String.format("%s already exists", toEditIn));
            }
            model.deleteRelationship(toEditOff);
            model.addRelationship(toEditIn);
            return new CommandResult(MESSAGE_EDIT_RELATIONSHIP_SUCCESS);
        } catch (IllegalArgumentException e) {
            throw new CommandException(String.format(e.getMessage()));
        }
    }
}
