package seedu.address.model.person;

// Gender attribute with enum
class GenderAttribute extends Attribute {
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }

    public GenderAttribute(String name, Gender gender) {
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
