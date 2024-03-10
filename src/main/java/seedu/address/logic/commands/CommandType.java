package seedu.address.logic.commands;

import seedu.address.logic.util.exceptions.ParseException;

/**
 * Factory enumeration class for {@code Command} objects.
 */
public enum CommandType {
    ADD {
        @Override
        public Command createCommand(String arguments) throws ParseException {
            return AddCommand.of(arguments);
        }
    },
    EDIT {
        @Override
        public Command createCommand(String arguments) throws ParseException {
            return EditCommand.of(arguments);
        }
    },
    DELETE {
        @Override
        public Command createCommand(String arguments) throws ParseException {
            return DeleteCommand.of(arguments);
        }
    },
    CLEAR {
        @Override
        public Command createCommand(String arguments) {
            return new ClearCommand();
        }
    },
    FIND {
        @Override
        public Command createCommand(String arguments) throws ParseException {
            return FindCommand.of(arguments);
        }
    },
    LIST {
        @Override
        public Command createCommand(String arguments) {
            return new ListCommand();
        }
    },
    EXIT {
        @Override
        public Command createCommand(String arguments) {
            return new ExitCommand();
        }
    },
    HELP {
        @Override
        public Command createCommand(String arguments) {
            return new HelpCommand();
        }
    };

    public abstract Command createCommand(String arguments) throws ParseException;
}
