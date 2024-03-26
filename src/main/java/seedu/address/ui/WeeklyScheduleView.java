package seedu.address.ui;

import java.time.DayOfWeek;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import seedu.address.model.schedule.Schedule;

/**
 * WeeklyScheduleView class to assist with population of Calender
 */
public class WeeklyScheduleView extends UiPart<Region> {

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

    /**
     * Constructor method to initialize class
     */
    public WeeklyScheduleView() {
        super(FXML);
    }

    /**
     * initialize call to set up CellFactory to populate Schedule objects
     */

    public void initialize() {
        // Customize the appearance of items in each ListView
        customizeListView(mondayListView);
        customizeListView(tuesdayListView);
        customizeListView(wednesdayListView);
        customizeListView(thursdayListView);
        customizeListView(fridayListView);
        customizeListView(saturdayListView);
        customizeListView(sundayListView);
        // Initialize the ListView elements
        mondayListView.setItems(FXCollections.observableArrayList());
        tuesdayListView.setItems(FXCollections.observableArrayList());
        wednesdayListView.setItems(FXCollections.observableArrayList());
        thursdayListView.setItems(FXCollections.observableArrayList());
        fridayListView.setItems(FXCollections.observableArrayList());
        saturdayListView.setItems(FXCollections.observableArrayList());
        sundayListView.setItems(FXCollections.observableArrayList());
        System.out.println("I've been set-up!");
    }

    private void customizeListView(ListView<Schedule> listView) {
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Schedule item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    //System.out.println("Cell is empty or item is null");
                } else {
                    setText(item.getSchedName() + " - " + item.getStartTime().getHour()
                            + item.getStartTime().getMinute() + " to "
                            + item.getEndTime().getHour() + item.getEndTime().getMinute());
                    //System.out.println("Cell content updated");
                }
            }
        });
    }

    private ObservableList<Schedule> filterSchedulesForDay(ArrayList<Schedule> schedules, DayOfWeek day) {
        //System.out.println("I got called to filter by day Schedules!");
        ObservableList<Schedule> filteredSchedules = FXCollections.observableArrayList(schedules);
        ObservableList<Schedule> filteredSchedulesDay = FXCollections.observableArrayList();
        for (Schedule schedule : filteredSchedules) {

            if (schedule.getStartTime().getDayOfWeek() == day) {
                filteredSchedulesDay.add(schedule);
            }
        }
        return filteredSchedulesDay;
    }

    // Method to populate ListView for a specific day
    private void populateListViewForDay(ArrayList<Schedule> schedules, ListView<Schedule> listView, DayOfWeek day) {
        //System.out.println("I got called to populateView!");
        ObservableList<Schedule> daySchedules = filterSchedulesForDay(schedules, day);
        for (Schedule schedule : daySchedules) {
            if (!listView.getItems().contains(schedule)) {
                assert (schedule != null);
                listView.getItems().add(schedule);
            }
        }
        //System.out.println("Schedules for " + day + ": " + daySchedules);
        //System.out.println("Current Calender for : "+ day + ": " + listView.getItems());
    }

    /**
     * Method to be called to update Calender with list of current week Schedules
     * @param schedules Array of Schedules for current week to be populated
     */
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

    // Method to filter schedules for a specific day

    void clear() {
        mondayListView.getItems().clear();
        tuesdayListView.getItems().clear();
        wednesdayListView.getItems().clear();
        thursdayListView.getItems().clear();
        fridayListView.getItems().clear();
        saturdayListView.getItems().clear();
        sundayListView.getItems().clear();
    }
}
