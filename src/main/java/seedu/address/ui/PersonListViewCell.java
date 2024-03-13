package seedu.address.ui;

import javafx.scene.control.ListCell;
import seedu.address.model.person.Person;

/**
 * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
 */
public class PersonListViewCell extends ListCell<Person> {
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
