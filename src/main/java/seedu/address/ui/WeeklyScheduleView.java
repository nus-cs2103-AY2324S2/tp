package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyScheduleView extends UiPart<Region>{

    private static final String FXML = "WeeklyScheduleView.fxml";
    @FXML
    private GridPane gridPane;

    @FXML
    private ListView<Schedule> mondayListView;

    @FXML
    private ListView<Schedule> tuesdayListView;

    @FXML
    private ListView<Schedule> wednesdayListView;

    @FXML
    private ListView<Schedule> thursdayListView;

    @FXML
    private ListView<Schedule> fridayListView;

    @FXML
    private ListView<Schedule> saturdayListView;

    @FXML
    private ListView<Schedule> sundayListView;
    private ArrayList<LocalDate> weekDates;

    public WeeklyScheduleView() {
        super(FXML);
        initialize();
    }

    public void initialize() {
        // Initialize the ListView elements
        mondayListView.setItems(FXCollections.observableArrayList());
        tuesdayListView.setItems(FXCollections.observableArrayList());
        wednesdayListView.setItems(FXCollections.observableArrayList());
        thursdayListView.setItems(FXCollections.observableArrayList());
        fridayListView.setItems(FXCollections.observableArrayList());
        saturdayListView.setItems(FXCollections.observableArrayList());
        sundayListView.setItems(FXCollections.observableArrayList());

        // Customize the appearance of items in each ListView
        customizeListView(mondayListView);
        customizeListView(tuesdayListView);
        customizeListView(wednesdayListView);
        customizeListView(thursdayListView);
        customizeListView(fridayListView);
        customizeListView(saturdayListView);
        customizeListView(sundayListView);
    }

    private void customizeListView(ListView<Schedule> listView) {
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Schedule item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getSchedName() + " - " + item.getStartTime() + " to " + item.getEndTime());
                    // Customize the format as needed to display all details of the Schedule
                }
            }
        });
    }

    public void populateWeeklySchedule(ArrayList<Schedule> schedules) {
        System.out.println("I got called to populateWeeklySchedule!");
        populateListViewForDay(schedules, mondayListView, DayOfWeek.MONDAY);
        populateListViewForDay(schedules, tuesdayListView, DayOfWeek.TUESDAY);
        populateListViewForDay(schedules, wednesdayListView, DayOfWeek.WEDNESDAY);
        populateListViewForDay(schedules, thursdayListView, DayOfWeek.THURSDAY);
        populateListViewForDay(schedules, fridayListView, DayOfWeek.FRIDAY);
        populateListViewForDay(schedules, saturdayListView, DayOfWeek.SATURDAY);
        populateListViewForDay(schedules, sundayListView, DayOfWeek.SUNDAY);
    }

    // Method to populate ListView for a specific day
    private void populateListViewForDay(ArrayList<Schedule> schedules, ListView<Schedule> listView, DayOfWeek day) {
        System.out.println("I got called to populateView!");
        ObservableList<Schedule> daySchedules = filterSchedulesForDay(schedules, day);
        if (!daySchedules.isEmpty()) {
//            for (Schedule sched : daySchedules) {
//                listView.getItems().add(sched);
//            }
            listView.setItems(daySchedules);
            listView.setCellFactory(param -> new ScheduleListCell());
        }
    }

    // Method to filter schedules for a specific day
    private ObservableList<Schedule> filterSchedulesForDay(ArrayList<Schedule> schedules, DayOfWeek day) {
        System.out.println("I got called to filter by day Schedules!");
        ObservableList<Schedule> filteredSchedules = FXCollections.observableArrayList(schedules);
        ObservableList<Schedule> filteredSchedulesDay = FXCollections.observableArrayList();
        for (Schedule schedule : filteredSchedules) {
            if (schedule.getStartTime().getDayOfWeek() == day) {
                filteredSchedulesDay.add(schedule);
            }
        }
        return filteredSchedules;
    }

    private class ScheduleListCell extends ListCell<Schedule> {
        @Override
        protected void updateItem(Schedule item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                // Create a custom layout for displaying schedule details
                GridPane gridPane = new GridPane();
                gridPane.setHgap(10);

                Label nameLabel = new Label(item.getSchedName());
                Label startTimeLabel = new Label(item.getStartTime().toString());
                Label endTimeLabel = new Label(item.getEndTime().toString());

                // Add labels to grid pane
                gridPane.addRow(0, nameLabel);
                gridPane.addRow(1, startTimeLabel);
                gridPane.addRow(2, endTimeLabel);

                // Set the custom layout as the cell content
                setGraphic(gridPane);
            }
        }
    }
}