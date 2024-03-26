package seedu.address.ui;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

@ExtendWith(ApplicationExtension.class)
public class ConfirmationBoxTest {
    private Button testButton;

    @Start
    public void start(Stage stage) {
        testButton = new Button("Delete");
        testButton.setId("deleteButton");
        testButton.setOnAction(event -> {
            boolean result = new ConfirmationBox().display("Confirm", "Are you sure?");
        });

        Scene scene = new Scene(new StackPane(testButton), 200, 100);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testConfirmationBox(FxRobot robot) {
        robot.clickOn("#deleteButton");
        robot.clickOn("OK");
    }
}

