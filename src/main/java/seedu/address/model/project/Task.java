package seedu.address.model.project;

import java.time.LocalDate;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

public class Task {

    // Identity fields
    private final Name taskName;

    private Person person;

    private boolean status;
    private Integer progressCounter = 0;

    private LocalDate deadlineDate;

    private Integer priorityNumber;

    public Task(String name) {
        requireAllNonNull(name);
        this.taskName = new Name(name);
        this.status = true;
    }

    public void assignPerson(Person person) {
        this.person = person;
    }

    public void setComplete() {
        this.status = false;
    }

    public void setIncomplete() {
        this.status = true;
        progressCounter = progressCounter + 1;
    }

    public String getStatus() {
        if (status) {
            if (progressCounter > 0) {
                return "In progress";
            } else {
                return "Incomplete";
            }
        } else {
            return "Complete";
        }
    }

    public void setDeadline(String deadline) {
        this.deadlineDate = LocalDate.parse(deadline);
    }

    public void setPriority(Integer priorityNumber) {
        this.priorityNumber = priorityNumber;
    }

}
