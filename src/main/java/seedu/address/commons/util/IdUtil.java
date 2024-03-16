package seedu.address.commons.util;

import static java.util.Objects.requireNonNull;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Random;

import jdk.jshell.spi.ExecutionControl;

/**
 * Generates unique  String IDs for patients, doctors, and appointments
 */
public class IdUtil {

    /**
     * Enum containing all possible entity types in our system
     */
    public enum Entities {
        PATIENT("p"),
        DOCTOR("d"),
        APPOINTMENT("a");

        private final String letter;
        Entities(String letter) {
            this.letter = letter;
        }

        /**
         * Returns letter associated with entity
         * @return String letter
         */
        public String getLetter() {
            return letter;
        }

        /**
         * Gets entity object associated with character
         *
         * @param c character in question
         * @return Entities entity object associated with input character
         */
        protected static Entities getEntityFromChar(char c) {
            if (c == 'a') {
                return Entities.APPOINTMENT;
            } else if (c == 'p') {
                return Entities.PATIENT;
            } else if (c == 'D') {
                return Entities.DOCTOR;
            }
            throw new IllegalArgumentException("Invalid character input - no corresponding entity");
        }
    }

    // EnumMap storing entities and their corresponding used up ids.
    private static final EnumMap<Entities, HashSet<String>> allIds = new EnumMap<>(Entities.class);

    /**
     * Generates a new id based on input entity.
     *
     * @param entity type of id to generate
     * @return String id
     */
    public static String generateNewId(Entities entity) {
        HashSet<String> idSet = allIds.get(entity);
        if (idSet == null) {
            idSet = new HashSet<>();
        }
        Random random = new Random();
        String initId = String.valueOf(random.nextInt(90000000) + 10000000);
        while (idSet.contains(initId)) {
            initId = String.valueOf(random.nextInt(90000000) + 10000000);;
        }
        idSet.add(initId);

        return entity.getLetter() + initId;
    }

    /**
     * Deletes Id that is inputted
     * @param id String id to delete
     */
    public static void deleteId(String id) {
        requireNonNull(id);
        Entities entity = Entities.getEntityFromChar(id.substring(0, 1).charAt(0));
        HashSet<String> idSet = allIds.get(entity);
        idSet.remove(id.substring(1, id.length()));
    }

    /**
     * Updates map with initial values from storage
     * @throws ExecutionControl.NotImplementedException
     */
    public static void initalMapUpdate() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("to be implemented");
    }
}
