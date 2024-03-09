package seedu.address.logic.commands;

import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Factory enumeration class for Command objects.
 */
public enum CommandType {
    ADD {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    EDIT {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    DELETE {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    CLEAR {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    FIND {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    LIST {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    EXIT {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    },
    HELP {
        @Override
        public Command createCommand(String arguments) {
            return null;
        }
    };

    public abstract Command createCommand(String arguments) throws ParseException;
}
