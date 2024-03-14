package seedu.address;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
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
     * Generates a QR code based on the provided person information and saves it to the specified file path.
     *
     * @param person   the person object containing the information to encode in the QR code
     * @param filePath the file path where the QR code image will be saved
     * @param width    the width of the QR code image
     * @param height   the height of the QR code image
     * @throws WriterException if an error occurs during the encoding process
     * @throws IOException     if an error occurs while writing the QR code image to the file
     */
    public static void generateQrCode(Person person, String filePath, int width,
                                      int height) throws WriterException, IOException {
        String vCard = createVCardString(person);

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 1); // Set the margin to avoid cutting off
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(vCard, BarcodeFormat.QR_CODE, width, height, hints);

        Path path = FileSystems.getDefault().getPath(filePath);
        Files.createDirectories(path.getParent()); // Create necessary folders along the path if they do not exist
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

    private static String createVCardString(Person person) {
        return "BEGIN:VCARD\n"
                + "VERSION:3.0\n"
                + "N:" + person.getName() + ";;;\n"
                + "FN:" + person.getName() + "\n"
                + "TEL;TYPE=CELL:" + person.getPhone() + "\n"
                + "END:VCARD";
    }

    // public static void main(String[] args) {
    //     try {
    //         generateVCardQRCode("John Doe", "+1234567890", "./qrcode.png", 200, 200);
    //         System.out.println("QR Code generated successfully.");
    //     } catch (WriterException | IOException e) {
    //         System.err.println("Could not generate QR Code, WriterException :: " + e.getMessage());
    //     }
    // }
}
