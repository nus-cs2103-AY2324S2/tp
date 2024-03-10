package seedu.address.logic.commands;

/**
 * Factory enumeration class for {@code Command} objects.
 */
public enum CommandType {
    ADD {
        @Override
        public Command createCommand(String arguments) throws IllegalArgumentException {
            return AddCommand.of(arguments);
        }
    },
    EDIT {
        @Override
        public Command createCommand(String arguments) throws IllegalArgumentException {
            return EditCommand.of(arguments);
        }
    },
    DELETE {
        @Override
        public Command createCommand(String arguments) throws IllegalArgumentException {
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
        public Command createCommand(String arguments) throws IllegalArgumentException {
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

    public abstract Command createCommand(String arguments) throws IllegalArgumentException;
}
