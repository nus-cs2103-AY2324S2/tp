package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class ViewPanel extends UiPart<Region> {
    private static final String FXML = "ViewPanel.fxml";

    public final Person person;

    @FXML
    private VBox viewPanel;
    @FXML
    private Label companyName;
    @FXML
    private Label jobDescription;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label tag;
    @FXML
    private Label interviewDate;
    @FXML
    private Label internDuration;
    @FXML
    private Label salary;
    @FXML
    private Label note;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} to display.
     */
    public ViewPanel(Person person) {
        super(FXML);
        this.person = person;
        companyName.setText(person.getCompanyName().fullName);
        jobDescription.setText(person.getJobDescription().value);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        interviewDate.setText("Interview : " + person.getInterviewDate().toString());
        tag.setText(person.getTag().getTagName());
        internDuration.setText(person.getInternDuration().value);
        salary.setText("$" + person.getSalary().value);
        note.setText(person.getNote().value);
    }
}
