package in.codelessons.qr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author Praveen Rawat
 *
 */
public class QRCodeExample {

	public static void main(String[] args) throws WriterException, IOException {
		int imageSize = 240;
		String imagePath = "qrcode.png";
		String imageType = "png";
		String qrContent = "https://www.codelessons.in/";

		generateQRCode(imagePath, qrContent, imageSize, imageType);

		System.out.println("QR Code Generated.");
	}

	public static void generateQRCode(String imagePath, String content, int size, String imageType)
			throws WriterException, IOException {
		File file = new File(imagePath);
		Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		ImageIO.write(image, imageType, file);
	}

}
