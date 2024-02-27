package seedu.address.model.person;

class Relationship {
    private String type;
    private Person person1;
    private Person person2;

    public Relationship(String type, Person person1, Person person2) {
        this.type = type;
        this.person1 = person1;
        this.person2 = person2;
    }

    // Getters and setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Person getPerson1() {
        return person1;
    }

    public void setPerson1(Person person1) {
        this.person1 = person1;
    }

    public Person getPerson2() {
        return person2;
    }

    public void setPerson2(Person person2) {
        this.person2 = person2;
    }
}