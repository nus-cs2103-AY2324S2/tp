package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * Displays a person's information
 */
public class DisplayCard extends UiPart<Region> {

    private static final String FXML = "DisplayCard.fxml";
    private static final String TAG_LABEL = "Tags: ";
    private static final String PHONE_LABEL = "Phone Number: ";
    private static final String ADDRESS_LABEL = "Address: ";
    private static final String EMAIL_LABEL = "Email: ";
    private static final String REMARK_LABEL = "Remarks: ";
    private static final String BIRTHDAY_LABEL = "Birthday: ";
    private static final String MONEY_LABEL = "Money Owed: ";

    public final Person person;

    @FXML
    private HBox displayPane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label address;
    @FXML
    private Label addressLabel;
    @FXML
    private Label email;
    @FXML
    private Label emailLabel;
    @FXML
    private FlowPane tags;
    @FXML
    private Label tagLabel;
    @FXML
    private Label birthday;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label remark;
    @FXML
    private Label remarkLabel;
    @FXML
    private Label moneyOwed;
    @FXML
    private Label moneyLabel;

    /**
     * @param person Person information to be displayed on the card
     */
    public DisplayCard(Person person) {
        super(FXML);

        this.person = person;
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        remark.setText(person.getRemark().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        birthday.setText(person.getBirthday().toString());
        moneyOwed.setText(person.getMoneyOwed().getMessage());
        tagLabel.setText(TAG_LABEL);
        phoneLabel.setText(PHONE_LABEL);
        addressLabel.setText(ADDRESS_LABEL);
        emailLabel.setText(EMAIL_LABEL);
        remarkLabel.setText(REMARK_LABEL);
        birthdayLabel.setText(BIRTHDAY_LABEL);
        moneyLabel.setText(MONEY_LABEL);
    }

    public HBox getDisplayPane() {
        return displayPane;
    }
}
