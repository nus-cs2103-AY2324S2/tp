package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;


import org.junit.jupiter.api.Test;

public class CourseNameTest {

    private final CourseName courseName = new CourseName();

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> courseName.resetData(null));
    }


}
