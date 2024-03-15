package seedu.address.model.person;

public class Id {
    public static final String MESSAGE_CONSTRAINTS =
            "Ids should only contain numbers";
    private int id;
    public Id(int id) {
        this.id = id;
    }

    public static boolean isValidId(String test) {
        try {
            Integer.parseInt(test);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

}
