package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.address.model.person.PolicyList;
import seedu.address.model.policy.Policy;


/**
 * An UI component that displays policies of a {@code Person} in a TableView.
 */
public class ClientPolicyTable extends UiPart<Region> {

    private static final String FXML = "ClientPolicyTable.fxml";

    @FXML
    private TableView<Policy> policyTableView;
    @FXML
    private TableColumn<Policy, String> policyId;
    @FXML
    private TableColumn<Policy, String> policyName;
    // @FXML
    // private TableColumn<Policy, String> policyType;

    /**
     * Creates an empty {@code ClientPolicyTable}.
     */
    public ClientPolicyTable() {
        super(FXML);
    }

    /**
     * Creates a {@code ClientPolicyTable} with the given {@code policyList}.
     */
    public ClientPolicyTable(PolicyList policyList) {
        super(FXML);
        policyId.setCellValueFactory(new PropertyValueFactory<Policy, String>("policyId"));
        policyName.setCellValueFactory(new PropertyValueFactory<Policy, String>("policyName"));
        // policyType.setCellValueFactory(new PropertyValueFactory<Policy, String>("policyType"));
        policyTableView.setItems(policyList.policyList);
    }

}
