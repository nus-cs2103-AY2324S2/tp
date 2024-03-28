package seedu.address.model.person.attribute;

/**
 * Gender attribute with enum value
 */
public class SexAttribute extends Attribute {
    private Gender gender;

    /**
     * Gender enum
     */
    public enum Gender {
        MALE, FEMALE
    }
    /**
     * Constructor for SexAttribute
     *
     * @param name Name of the attribute
     * @param gender Sex of the person
     */
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
