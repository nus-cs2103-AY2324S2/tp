package seedu.address.logic.paynow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * This helper class helps to generate a QR Code image given a string.
 */
public class QrGenerator {
    private static final int SIZE = 400;
    private static final Color PAYNOW_COLOR = Color.decode("#7a1b78");
    private static final String LOGO_PATH = Paths.get(
            "src", "main", "resources", "images", "paynowlogo.png").toString();

    private static void overlayLogo(Graphics2D graphics) throws IOException {
        Image logo = getLogo();
        int startHeight = (SIZE - logo.getHeight(null)) / 2;
        int startWidth = (SIZE - logo.getWidth(null)) / 2;
        graphics.drawImage(logo, startWidth, startHeight, null);
    }

    private static Image getLogo() throws IOException {
        BufferedImage originalLogo = ImageIO.read(new File(LOGO_PATH));
        float scale = (float) SIZE / 6 / originalLogo.getHeight();
        return originalLogo.getScaledInstance(
                (int) (originalLogo.getWidth() * scale), (int) (originalLogo.getHeight() * scale), Image.SCALE_SMOOTH);
    }

    /**
     * Converts the given text into a QR Code and returns the Image.
     */
    public static BufferedImage generateQrCode(String qrCodeText) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, SIZE, SIZE, hintMap);

        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(PAYNOW_COLOR);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        overlayLogo(graphics);
        return image;
    }
}
