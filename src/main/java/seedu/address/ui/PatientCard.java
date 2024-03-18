package seedu.address.ui;

import java.util.ArrayList;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.patient.ImportantDate;
import seedu.address.model.patient.Patient;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label patientHospitalId;
    @FXML
    private Label preferredName;
    @FXML
    private Label foodPreference;
    @FXML
    private Label familyCondition;
    @FXML
    private Label hobby;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox importantDates;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        patientHospitalId.setText(patient.getPatientHospitalId().patientHospitalId);
        name.setText(patient.getName().fullName);
        preferredName.setText(patient.getPreferredName().preferredName);
        foodPreference.setText(patient.getFoodPreference().foodPreference);
        familyCondition.setText(patient.getFamilyCondition().familyCondition);
        hobby.setText(patient.getHobby().hobby);
        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        if (patient.getImportantDates().size() >= 1) {
            importantDates.getChildren().add(new Label("Upcoming:\n"));

            ArrayList<ImportantDate> allImportantDates = new ArrayList<>(patient.getImportantDates());
            for (int i = 1; i <= allImportantDates.size(); i++) {
                importantDates.getChildren().add(new Label((i) + ". "
                        + allImportantDates.get(i - 1).toString() + "\n"));
            }
        }
    }
}
