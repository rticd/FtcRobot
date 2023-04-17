package org.firstinspires.ftc.teamcode.Autonomous.Camera;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;
import com.google.zxing.Result;

import org.firstinspires.ftc.teamcode.Autonomous.AutoBlue;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.BlueConeDetectionUtil;

import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.ReadQRCode;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.YellowPoleDetectionUtil;
import org.firstinspires.ftc.teamcode.Autonomous.State;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {
    OpenCvWebcam webcam;
    boolean viewportPaused;

    FtcDashboard dashboard;
    public Result result;
    public Pipeline(OpenCvWebcam webcam,FtcDashboard dashboard){
        this.webcam = webcam;
        this.dashboard =dashboard;
    }
    @Override
    public Mat processFrame(Mat input) {
        Bitmap bitmap = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
        if (AutoBlue.currentAction == State.Scan) {
            Utils.matToBitmap(input, bitmap);
            if (ReadQRCode.readQRCode(bitmap)!=null){
                AutoBlue.parkingPosition = ReadQRCode.readQRCode(bitmap).getText();
            }
        } else if (AutoBlue.currentAction == State.toCone || AutoBlue.currentAction==State.RotateCone) {
            final Mat extractedColors = BlueConeDetectionUtil.extractColors(input);
            // Process frame
            final Mat processed = BlueConeDetectionUtil.processImage(extractedColors);
            // Mark outer contour
            BlueConeDetectionUtil.markOuterContour(processed, extractedColors);
            Utils.matToBitmap(extractedColors, bitmap);
            input.release();
            extractedColors.copyTo(input);
            extractedColors.release();
            processed.release();
        } else if(AutoBlue.currentAction == State.toPole|| AutoBlue.currentAction==State.RotatePole){
            final Mat extractedColors = YellowPoleDetectionUtil.extractColors(input);
            // Process frame
            final Mat processed = YellowPoleDetectionUtil.processImage(extractedColors);
            // Mark outer contour
            YellowPoleDetectionUtil.markOuterContour(processed, extractedColors);
            Utils.matToBitmap(extractedColors, bitmap);
            input.release();
            extractedColors.copyTo(input);
            extractedColors.release();
            processed.release();
        }

        dashboard.sendImage(bitmap);
        return input;
    }


    @Override
    public void onViewportTapped() {
        viewportPaused = !viewportPaused;
        if (viewportPaused) {
            this.webcam.pauseViewport();
        } else {
            this.webcam.resumeViewport();
        }
    }
}