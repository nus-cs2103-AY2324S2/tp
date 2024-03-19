package seedu.address.logic;

/**
 * State of the program/command box, depending on the state,
 * the command box will accept different arguments.
 */
public enum CommandBoxState {
    NORMAL,
    CLEARCONFIRM,
    DELETECONFIRM
}
