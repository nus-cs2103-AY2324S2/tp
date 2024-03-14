package seedu.address.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.coursemate.CourseMate;

/**
 * An UI component that displays detailed information of a {@code CourseMate}.
 */
public class CourseMateDetailPanel extends UiPart<Region> {
    private static final String FXML = "CourseMateDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(CourseMateDetailPanel.class);

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private FlowPane skills;

    /**
     * Creates a {@code CourseMateDetailPanel} with the given {@code CourseMate}.
     */
    public CourseMateDetailPanel(CourseMate courseMate) {
        super(FXML);
        loadCourseMate(courseMate);
    }

    /**
     * Changes the {@code CourseMateDetailPanel} to display the given {@code CourseMate}.
     */
    public void loadCourseMate(CourseMate courseMate) {
        id.setText("## ");
        name.setText(courseMate.getName().fullName);
        phone.setText(courseMate.getPhone().value);
        address.setText(courseMate.getAddress().value);
        email.setText(courseMate.getEmail().value);
        skills.getChildren().clear();
        courseMate.getSkills().stream()
                .sorted(Comparator.comparing(skill -> skill.skillName))
                .forEach(skill -> skills.getChildren().add(new Label(skill.skillName)));
    }
}
