package seedu.address.ui;

import javafx.fxml.FXML;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;


/**
 * An UI component that displays policies of a {@code Person} in a TableView.
 */
public class ClientPolicyTable extends UiPart<Region> {

    private static final String FXML = "ClientPolicyTable.fxml";

    /*
    @FXML
    private TableView<Policy> policyTableView;
    @FXML
    private TableColumn<Policy, String> policyId;
    @FXML
    private TableColumn<Policy, String> policyName;
    */

    /**
     * Creates a {@code ClientPolicyTable} with the given {@code } and index to display.
     */
    public ClientPolicyTable() {
        // argument should be some kind of list? ObservableList maybe
        super(FXML);
        // policyId.setCellValueFactory(new PropertyValueFactory<Policy, String>("policyId"));
        // policyTableView.setItems(xxx);
    }
}
