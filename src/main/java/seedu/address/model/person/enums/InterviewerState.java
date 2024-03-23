package seedu.address.model.person.enums;

/**
 * Enumerates an Interviewer's status at any given time.
 */
public enum InterviewerState {
    FREE {
        public String toString() {
            return "free";
        }
    },
    OCCUPIED {
        public String toString() {
            return "interview with";
        }
    }
}
