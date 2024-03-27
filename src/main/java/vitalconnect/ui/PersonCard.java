package vitalconnect.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import vitalconnect.model.person.Person;
import vitalconnect.model.person.contactinformation.ContactInformation;
import vitalconnect.model.person.medicalinformation.MedicalInformation;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on Clinic level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label nric;
    @FXML
    private FlowPane tags;
    @FXML
    private Label contactInformation;
    @FXML
    private Label medicalInformation;
    @FXML
    private Label allergy;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        ContactInformation ci = person.getContactInformation();
        MedicalInformation mi = person.getMedicalInformation();
        String contactInformationText = "";
        if (!ci.isEmptyContact()) {
            contactInformationText = ci.toString();
        }
        String medicalInformationText = "";
        if (!mi.isEmpty()) {
            medicalInformationText = mi.toString();
        }

        id.setText(displayedIndex + ". ");
        name.setText(person.getIdentificationInformation().getName().fullName);
        nric.setText(person.getIdentificationInformation().getNric().nric);
        contactInformation.setText(contactInformationText);
        medicalInformation.setText(medicalInformationText);

        // Set allergy label visibility based on medical information and allergy tags
        if (!mi.isEmpty() && !mi.getAllergyTag().isEmpty()) {
            allergy.setVisible(true);
            allergy.setText("Allergic to: ");
            mi.getAllergyTag().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        } else {
            allergy.setVisible(false);
            allergy.setText("");
        }
    }
}


