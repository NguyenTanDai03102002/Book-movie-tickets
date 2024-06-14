package vnpt.movie_booking_be.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class QRCODE {
    @Autowired
    private Cloudinary cloudinary;

    public static byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException, IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
    public String generateQRCodeAndUpload(String url, int width, int height) throws WriterException, IOException {
        // Tạo mã QR code từ URL
        byte[] qrCodeImage = QRCODE.generateQRCodeImage(url, width, height);

        // Chuyển đổi byte array sang định dạng base64
        String qrCodeBase64 = Base64.encodeBase64String(qrCodeImage);

        // Tải lên hình ảnh QR code lên Cloudinary và lấy đường dẫn URL
        return uploadImageToCloudinary(qrCodeBase64);
    }

    private String uploadImageToCloudinary(String base64Image) {
        try {
            // Chuyển đổi base64 sang byte array
            byte[] imageData = Base64.decodeBase64(base64Image);

            // Tạo map rỗng cho các tham số bổ sung (nếu cần)
            Map<String, Object> params = ObjectUtils.emptyMap();

            // Upload hình ảnh lên Cloudinary và lấy kết quả trả về
            Map<String, Object> uploadResult = cloudinary.uploader().upload(imageData, params);

            // Lấy đường dẫn URL của hình ảnh từ kết quả upload
            return (String) uploadResult.get("url");
        } catch (IOException io) {
            throw new RuntimeException("Image upload failed", io);
        }
    }
}
