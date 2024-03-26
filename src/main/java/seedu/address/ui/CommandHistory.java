package seedu.address.ui;

import java.util.ArrayList;

/**
 * Contains the command history of the user.
 */
public class CommandHistory {

   private static ArrayList<String> commandHistory;
   private static Integer toReturn = 0;

   private static String latestCommandInEdit = "";

   private CommandHistory() {
       commandHistory = new ArrayList<>();
   }

   public static void appendCommand(String command) {
       if (commandHistory == null) {
           new CommandHistory();
       }

       if (command == null) {
           return;
       }

       commandHistory.add(command);

       toReturn = commandHistory.size();

   }

    public static String getCommandHistory(String direction) {

        if (commandHistory == null) {
            return "";
        }

        if (direction.equals("up") && toReturn > 0) {
            toReturn--;
            return commandHistory.toArray()[toReturn].toString();
        } else if (direction.equals("up") && toReturn == 0) {
            return commandHistory.toArray()[toReturn].toString();
        } else if (direction.equals("down") && toReturn == commandHistory.size()) {
            return "";
        } else if (direction.equals("down") && toReturn < commandHistory.size()) {
            toReturn++;
            if (toReturn == commandHistory.size()) {
                return "";
            }
            return commandHistory.toArray()[toReturn].toString();
        } else {
            return "";
        }
    }

    public static void clearCommandHistory() {
        commandHistory = null;
        toReturn = 0;
    }
}
