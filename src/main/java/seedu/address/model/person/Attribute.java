package seedu.address.model.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.time.LocalDate;

abstract class Attribute {
    protected String name;

    public Attribute(String name) {
        this.name = name;
    }

    public abstract String getValueAsString();

    public String getName() {
        return name;
    }
}
