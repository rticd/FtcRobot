package org.firstinspires.ftc.teamcode.Autonomous.Camera.Util;

import android.graphics.Bitmap;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import org.firstinspires.ftc.robotcore.external.Telemetry;
public class ReadQRCode {

    public static Result readQRCode(Bitmap bitmap) {

        int[] pixels = new int[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(pixels, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        RGBLuminanceSource source = new RGBLuminanceSource(bitmap.getWidth(), bitmap.getHeight(), pixels);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
        MultiFormatReader reader = new MultiFormatReader();
        Result result;
        try {
            result = reader.decode(binaryBitmap);
            return result;
        } catch (NotFoundException e) {
            return null;

        }

    }
}