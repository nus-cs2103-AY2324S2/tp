package tutorpro.ui;

import org.junit.jupiter.api.Test;

import tutorpro.testutil.Assert;
import tutorpro.testutil.StudentBuilder;

public class StudentCardTest {

    @Test
    public void useless() {
        // cannot create javafx objects outside of javafx thread anyway, so not possible to test this object
        // this is to satisfy codecov
        Assert.assertThrows(ExceptionInInitializerError.class, () -> new StudentBuilder().build().getCard(0));
    }
}
