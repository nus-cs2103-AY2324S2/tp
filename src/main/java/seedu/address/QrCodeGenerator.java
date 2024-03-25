package seedu.address;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import seedu.address.model.person.Person;

/**
 * Class to generate QR Codes
 */
public class QrCodeGenerator {
    /**
     * The folder where QR codes are stored.
     */
    public static final Path QR_CODE_FOLDER = Paths.get("data", "qrcodes");
    /**
     * The width of the QR code.
     */
    private static final int QR_CODE_WIDTH = 200;
    /**
     * The height of the QR code.
     */
    private static final int QR_CODE_HEIGHT = 200;

    /**
     * Generates a QR code based on the provided person information and saves it to the specified file path
     *
     * @param person the person object containing the information to encode in the QR code
     * @throws WriterException if an error occurs during the encoding process
     * @throws IOException     if an error occurs while writing the QR code image to the file
     */
    public static void generateQrCode(Person person) throws WriterException, IOException {
        String vCard = createVCardString(person);

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1); // Set the margin to avoid cutting off
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(vCard, BarcodeFormat.QR_CODE, QR_CODE_WIDTH, QR_CODE_HEIGHT, hints);

        Path path = getQrCodePath(person);
        Files.createDirectories(path.getParent()); // Create necessary folders along the path if they do not exist
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    /**
     * Creates a VCard string for the given {@code Person}
     *
     * @param person the person for whom the VCard string is created
     * @return the VCard string
     */
    private static String createVCardString(Person person) {
        return "BEGIN:VCARD\n"
                + "VERSION:3.0\n"
                + "N:" + person.getName() + ";;;\n"
                + "FN:" + person.getName() + "\n"
                + "TEL;TYPE=CELL:" + person.getPhone() + "\n"
                + "END:VCARD";
    }

    /**
     * Generates the file path for the QR code of a specific person
     *
     * @param person the person for whom the QR code path is generated
     * @return the file path for the QR code
     */
    public static Path getQrCodePath(Person person) {
        return Paths.get(QR_CODE_FOLDER.toString(),
                person.getName().toString() + "_" + person.getPhone().toString() + ".png");
    }
}
