package seedu.address.model;

import java.time.LocalDateTime;

public class Appointment {
    private String patientName;
    private LocalDateTime dateTime;

    public Appointment(String patientName, LocalDateTime dateTime) {
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    // Getters
    public String getPatientName() {
        return patientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}

