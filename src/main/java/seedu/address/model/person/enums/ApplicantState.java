package seedu.address.model.person.enums;

/**
 * Enumerates an applicant's statuses at any given time
 */
public enum ApplicantState {
    STAGE_ONE {
        public String toString() {
            return "resume review";
        }
    },
    STAGE_TWO {
        public String toString() {
            return "pending interview";
        }
    },
    STAGE_THREE {
        public String toString() {
            return "completed interview";
        }
    },
    OUTCOME_ONE {
        public String toString() {
            return "waiting list";
        }
    },
    OUTCOME_TWO {
        public String toString() {
            return "accepted";
        }
    },
    OUTCOME_THREE {
        public String toString() {
            return "rejected";
        }
    },
}
