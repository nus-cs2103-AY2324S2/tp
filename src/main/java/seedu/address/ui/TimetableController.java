package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import seedu.address.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimetableController {

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private TableColumn<Schedule, String> nameColumn;

    @FXML
    private TableColumn<Schedule, LocalDateTime> startTimeColumn;

    @FXML
    private TableColumn<Schedule, LocalDateTime> endTimeColumn;

    @FXML
    public void initialize() {
        // Initialize your table columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        // You can now add schedules to your table, possibly from an external source or statically for testing
        // Example:
        // scheduleTable.getItems().add(new Schedule("Meeting", LocalDateTime.now(), LocalDateTime.now().plusHours(1)));
        startTimeColumn.setCellFactory(column -> new TableCell<Schedule, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    // For example, format the LocalDateTime
                    setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        });

        endTimeColumn.setCellFactory(column -> new TableCell<Schedule, LocalDateTime>() {
            @Override
            protected void updateItem(LocalDateTime item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    // For example, format the LocalDateTime
                    setText(item.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }
        });
    }


}