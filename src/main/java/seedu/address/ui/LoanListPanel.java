package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Loan;
import seedu.address.model.person.Person;

/**
 * Panel containing the list of persons.
 */
public class LoanListPanel extends UiPart<Region> {
    private static final String FXML = "LoanListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LoanListPanel.class);

    @FXML
    private ListView<Loan> loanListView;

    @FXML
    private ListView<Person> personListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public LoanListPanel(ObservableList<Loan> loanList, ObservableList<Person> personList) {
        super(FXML);
        loanListView.setItems(loanList);
        loanListView.setCellFactory(listView -> new LoanListViewCell());
        personListView.setItems(personList);
        personListView.setCellFactory(listView -> new PersonListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class LoanListViewCell extends ListCell<Loan> {

        @Override
        protected void updateItem(Loan loan, boolean empty) {
            super.updateItem(loan, empty);

            if (empty || loan == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LoanCard(loan, getIndex() + 1).getRoot());
            }
        }
    }

    class PersonListViewCell extends ListCell<Person> {
        @Override
        protected void updateItem(Person person, boolean empty) {
            super.updateItem(person, empty);

            if (empty || person == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PersonCard(person, getIndex() + 1).getRoot());
            }
        }
    }

}
