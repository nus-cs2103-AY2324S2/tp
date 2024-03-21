package seedu.address.ui;

import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Client;
import seedu.address.model.person.Employee;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     * issue on NetConnect level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label index;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label remark;
    @FXML
    private FlowPane tags;
    @FXML
    private FlowPane role;
    @FXML
    private Label products;
    @FXML
    private Label preferences;
    @FXML
    private Label department;
    @FXML
    private Label jobTitle;
    @FXML
    private Label skills;
    @FXML
    private Label termsOfService;
    @FXML
    private Label id;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        index.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText("Phone: " + person.getPhone().value);
        address.setText("Address: " + person.getAddress().value);
        email.setText("Email: " + person.getEmail().value);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        remark.setText("Remark: " + person.getRemark().value);
        id.setText("ID: " + String.valueOf(person.getId().value));

        preferences.setVisible(false);
        department.setVisible(false);
        jobTitle.setVisible(false);
        products.setVisible(false);
        skills.setVisible(false);
        termsOfService.setVisible(false);

        preferences.setManaged(false);
        department.setManaged(false);
        jobTitle.setManaged(false);
        products.setManaged(false);
        skills.setManaged(false);
        termsOfService.setManaged(false);

        if (person instanceof Client) {
            Client client = (Client) person;
            role.getChildren().add(new Label("Client"));
            preferences.setVisible(true);
            preferences.setManaged(true);
            preferences.setText("Preferences: " + client.getPreferences());
            products.setVisible(true);
            products.setManaged(true);
            products.setText("Buying Products: "
                    + client.getProducts().getProducts().stream().collect(Collectors.joining(", ")));
        } else if (person instanceof Employee) {
            Employee employee = (Employee) person;
            role.getChildren().add(new Label("Employee"));
            department.setVisible(true);
            department.setManaged(true);
            department.setText("Department: " + employee.getDepartment().toString());
            jobTitle.setVisible(true);
            jobTitle.setManaged(true);
            jobTitle.setText("Job Title: " + employee.getJobTitle().toString());
            skills.setVisible(true);
            skills.setManaged(true);
            skills.setText("Skills: " + employee.getSkills().getSkills().stream()
                    .sorted().collect(Collectors.joining(", ")));
        } else if (person instanceof Supplier) {
            Supplier supplier = (Supplier) person;
            role.getChildren().add(new Label("Supplier"));
            termsOfService.setVisible(true);
            termsOfService.setManaged(true);
            termsOfService.setText("Terms of Service: " + supplier.getTermsOfService().toString());
            products.setVisible(true);
            products.setManaged(true);
            products.setText("Selling Products: "
                    + supplier.getProducts().getProducts().stream().collect(Collectors.joining(", ")));
        }

    }

}
