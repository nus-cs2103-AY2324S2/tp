package seedu.address.ui;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class MainWindowTest {

    @Test
    void useModuleView() {
        assertTrue(MainWindow.useModuleView("/list_classes"));
        assertTrue(MainWindow.useModuleView("/add_class module/CS2103T tutorial/T09"));
        assertTrue(MainWindow.useModuleView("/delete_class module/CS2103T tutorial/T09"));
        assertFalse(MainWindow.useModuleView("/list_students"));
    }
}
