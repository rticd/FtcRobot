package org.firstinspires.ftc.teamcode.Autonomous.Camera;

import android.graphics.Bitmap;

import com.acmerobotics.dashboard.FtcDashboard;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousEntryPoint;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.RotateConeBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.RotatePoleBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ScanBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ToConeBehaviour;
import org.firstinspires.ftc.teamcode.Autonomous.Behaviour.ToPoleBehaviour;

import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.ConeDetectionUtil;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.ReadQRCode;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.RelativePosition;
import org.firstinspires.ftc.teamcode.Autonomous.Camera.Util.YellowPoleDetectionUtil;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

public class Pipeline extends OpenCvPipeline {
    OpenCvWebcam webcam;
    boolean viewportPaused;
    public static FtcDashboard dashboard;
    public static String parkingPosition;
    public static double coneArea = 0;
    public static double poleArea = 0;
    public static RelativePosition conePosition;
    public static RelativePosition polePosition;
    public static boolean coneSelected;
    public static boolean poleSelected;

    public static ConeDetectionUtil coneDetectionUtil;

    public Pipeline(OpenCvWebcam webcam, ConeDetectionUtil coneDetectionUtil){
        this.webcam = webcam;
        this.dashboard =FtcDashboard.getInstance();
        this.coneDetectionUtil = coneDetectionUtil;
    }
    @Override
    public Mat processFrame(Mat input) {
        Bitmap bitmap = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
        if (AutonomousEntryPoint.currentBehaviour instanceof ScanBehaviour) {
            Utils.matToBitmap(input, bitmap);
            //Если parkingPosition ещё не найден, то сканить.
            if (ReadQRCode.readQRCode(bitmap) != null) {
                parkingPosition = ReadQRCode.readQRCode(bitmap).getText();
            }
        }
            //Насколько я могу понять, код внизу только выводит картинку на dashboard.
            //чтобы использовать его в Behaviour, создай в pipeline статические переменные с
            //нужной тебе информацией и назначи им значения внизу как тебе надо.
            //потом, ты сможешь использовать их как угодно в своих классах.

         else if (AutonomousEntryPoint.currentBehaviour instanceof ToConeBehaviour || AutonomousEntryPoint.currentBehaviour instanceof RotateConeBehaviour) {
            final Mat extractedColors = coneDetectionUtil.extractColors(input);
            // Process frame
            final Mat processed = coneDetectionUtil.processImage(extractedColors);
            // Mark outer contour
            coneDetectionUtil.markOuterContour(processed, extractedColors);
            Utils.matToBitmap(extractedColors, bitmap);
            input.release();
            extractedColors.copyTo(input);
            extractedColors.release();
            processed.release();
        }
         else if(AutonomousEntryPoint.currentBehaviour instanceof ToPoleBehaviour || AutonomousEntryPoint.currentBehaviour instanceof RotatePoleBehaviour) {
            final Mat extractedColorsYellow = YellowPoleDetectionUtil.extractColors(input);
            // Process frame
            final Mat processedYellow = YellowPoleDetectionUtil.processImage(extractedColorsYellow);
            // Mark outer contour
            YellowPoleDetectionUtil.markOuterContour(processedYellow, extractedColorsYellow);
            Utils.matToBitmap(extractedColorsYellow, bitmap);
            input.release();
            extractedColorsYellow.copyTo(input);
            extractedColorsYellow.release();
            processedYellow.release();
        }

        dashboard.sendImage(bitmap);
        return input;
    }


    @Override
    public void onViewportTapped() {
        this.webcam.pauseViewport();
        /*
        viewportPaused = !viewportPaused;
        if (viewportPaused) {
            this.webcam.pauseViewport();
        } else {
            this.webcam.resumeViewport();
        }*/
    }
}