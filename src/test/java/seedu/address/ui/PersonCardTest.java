package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;

import javafx.scene.control.Label;

@ExtendWith(ApplicationExtension.class)
public class PersonCardTest {
    private PersonCard personCard;

    @BeforeEach
    public void initPersonCard() {
        personCard = new PersonCard(ALICE, 1);
    }

    @Test
    public void constructor_validPerson_displayCorrectInformation() {
        assertEquals("1. ", personCard.getIdLabel().getText());
        assertEquals(ALICE.getName().fullName, personCard.getNameLabel().getText());
        assertEquals(ALICE.getPhone().value, personCard.getPhoneLabel().getText());
        assertEquals(ALICE.getEmail().value, personCard.getEmailLabel().getText());
        assertEquals("Year " + ALICE.getYear(), personCard.getYearLabel().getText());
        assertEquals(ALICE.getMajor().value, personCard.getMajorLabel().getText());
        assertEquals("@" + ALICE.getTelegram().value, personCard.getTelegramLabel().getText());
        assertEquals(ALICE.getRemark().value, personCard.getRemarkLabel().getText());
    }
}

