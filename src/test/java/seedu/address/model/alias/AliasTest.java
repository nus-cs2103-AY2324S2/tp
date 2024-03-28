package seedu.address.model.alias;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AliasTest {

    @Test
    public void getAlias() {
        Alias alias = new Alias();

        alias.addAlias("test", "result");

        assertTrue(alias.getAlias("test").equals("result"));

        alias.addAlias("test1", "result1");

        assertTrue(alias.getAlias("test1").equals("result1"));

        assertFalse(alias.getAlias("test1").equals("result"));

        assertFalse(alias.getAlias("test").equals("result1"));

        assertTrue(alias.getAlias("test3") == null);
    }

    @Test
    public void equals() {
        Alias alias = new Alias();

        Alias alias1 = new Alias();

        assertTrue(alias.equals(alias1));

        alias.addAlias("test", "result");

        assertFalse(alias.equals(alias1));

        alias1.addAlias("test", "result");

        assertTrue(alias.equals(alias1));

    }
}
