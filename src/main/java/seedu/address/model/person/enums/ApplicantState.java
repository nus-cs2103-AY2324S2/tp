package seedu.address.model.person.enums;

/**
 * Enumerates an applicant's statuses at any given time
 */
public enum ApplicantState {
    STAGEONE {
        public String toString() {
            return "resume review";
        }
    },
    STAGETWO {
        public String toString() {
            return "pending interview";
        }
    },
    STAGETHREE {
        public String toString() {
            return "completed interview";
        }
    },
    OUTCOMEONE {
        public String toString() {
            return "waiting list";
        }
    },
    OUTCOMETWO {
        public String toString() {
            return "accepted";
        }
    },
    OUTCOMETHREE {
        public String toString() {
            return "rejected";
        }
    },
}
