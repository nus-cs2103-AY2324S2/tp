package seedu.address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.google.zxing.WriterException;

class QrCodeGeneratorTest {

    @Test
    void generateQrCode_validPerson_success() throws IOException, WriterException {
        QrCodeGenerator.generateQrCode(ALICE);
        assertTrue(ALICE.getQrCodePath().toFile().exists());
    }

    @Test
    void getQrCodePath_validPerson_success() {
        Path path = QrCodeGenerator.getQrCodePath(ALICE);
        String expected = Paths.get(QrCodeGenerator.QR_CODE_FOLDER.toString(),
                ALICE.getName().toString() + "_" + ALICE.getPhone().toString() + ".png").toString();
        assertEquals(expected, path.toString());
    }
}