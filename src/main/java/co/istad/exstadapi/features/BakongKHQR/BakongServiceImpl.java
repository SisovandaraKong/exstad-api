package co.istad.exstadapi.features.BakongKHQR;

import co.istad.exstadapi.features.BakongKHQR.dto.BakongDataRequest;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import kh.gov.nbc.bakong_khqr.BakongKHQR;
import kh.gov.nbc.bakong_khqr.model.KHQRCurrency;
import kh.gov.nbc.bakong_khqr.model.KHQRData;
import kh.gov.nbc.bakong_khqr.model.KHQRResponse;
import kh.gov.nbc.bakong_khqr.model.MerchantInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class BakongServiceImpl implements BakongService{

    @Value("${bakong.account-id}")
    private String bakongAccountId;
    @Value("${bakong.acquiring-bank}")
    private String acquiringBank;
    @Value("${bakong.merchant-name}")
    private String merchantName;
    @Value("${bakong.mobile-number}")
    private String mobileNumber;
    @Value("${bakong.store-label}")
    private String storeLabel;

    // Generate QR for Merchant
    @Override
    public KHQRResponse<KHQRData> generateQR(BakongDataRequest request){
        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setBakongAccountId(bakongAccountId);
        merchantInfo.setMerchantId("123456");
        merchantInfo.setAcquiringBank(acquiringBank);
        merchantInfo.setCurrency(KHQRCurrency.USD);
        merchantInfo.setAmount(request.amount());
        merchantInfo.setMerchantName(merchantName);
        merchantInfo.setMerchantCity("PHNOM PENH");
        merchantInfo.setBillNumber("#12345");
        merchantInfo.setMobileNumber(mobileNumber);
        merchantInfo.setStoreLabel(storeLabel);
//        merchantInfo.setTerminalLabel("Cashier_1");
        merchantInfo.setUpiAccountInformation("KH123456789");
        merchantInfo.setMerchantAlternateLanguagePreference("km");
        merchantInfo.setMerchantNameAlternateLanguage("អាយស្តាត");
        merchantInfo.setMerchantCityAlternateLanguage("ភ្នំពញ");
        KHQRResponse<KHQRData> response = BakongKHQR.generateMerchant(merchantInfo);
        return response;
    }

    @Override
    public ResponseEntity<byte[]> getQRImage(KHQRData qr) {
        try {
            // Validate input
            if (qr == null || qr.getQr() == null || qr.getQr().isBlank()) {
                return ResponseEntity.badRequest()
                        .body("QR code data is missing or empty".getBytes(StandardCharsets.UTF_8));
            }

            String qrCodeText = qr.getQr();

            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, 300, 300, hints);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"qrcode.png\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .body(pngOutputStream.toByteArray());

        } catch (WriterException e) {
            // Thrown by QRCodeWriter.encode() if encoding fails (e.g., invalid data)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid QR code data".getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            // Thrown by MatrixToImageWriter.writeToStream()
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating QR image".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Fallback for any unexpected error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred".getBytes(StandardCharsets.UTF_8));
        }
    }
}
