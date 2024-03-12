package seedu.address.model.person.attribute;

// Gender attribute with enum
class SexAttribute extends Attribute {
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public SexAttribute(String name, Gender gender) {
        super(name);
        this.gender = gender;
    }

    @Override
    public String getValueAsString() {
        return gender.toString();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
