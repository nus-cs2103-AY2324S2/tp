package seedu.address.logic.paynow;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private static final int QR_SIZE = 400;
    private static final int LOGO_HEIGHT = QR_SIZE / 6;
    private static final Color PAYNOW_COLOR = Color.decode("#7a1b78");
    private static final String LOGO_PATH = "images/paynowlogo.png";

    private static void overlayLogo(Graphics2D graphics) throws IOException {
        Image logo = getLogo();
        int startHeight = (QR_SIZE - logo.getHeight(null)) / 2;
        int startWidth = (QR_SIZE - logo.getWidth(null)) / 2;
        graphics.drawImage(logo, startWidth, startHeight, null);
    }

    private static Image getLogo() throws IOException {
        BufferedImage originalLogo = ImageIO.read(ClassLoader.getSystemResourceAsStream(LOGO_PATH));
        float scale = (float) LOGO_HEIGHT / originalLogo.getHeight();
        return originalLogo.getScaledInstance(
                (int) (originalLogo.getWidth() * scale),
                (int) (originalLogo.getHeight() * scale),
                Image.SCALE_SMOOTH);
    }

    private static BitMatrix encodeText(String qrCodeText) throws WriterException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        return qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, QR_SIZE, QR_SIZE, hintMap);
    }

    private static BufferedImage fillImageFromBitMatrix(BitMatrix bitMatrix) {
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = bitMatrix.getWidth();
        int matrixHeight = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(matrixWidth, matrixHeight, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixHeight);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(PAYNOW_COLOR);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (bitMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        return image;
    }


    /**
     * Converts the given text into a QR Code and returns the Image.
     */
    public static ByteArrayInputStream generateQrCode(String qrCodeText) throws WriterException, IOException {
        BitMatrix bitMatrix = encodeText(qrCodeText);
        BufferedImage image = fillImageFromBitMatrix(bitMatrix);
        overlayLogo((Graphics2D) image.getGraphics());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", os);
        return new ByteArrayInputStream(os.toByteArray());
    }
}
