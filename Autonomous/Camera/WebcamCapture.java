
package org.firstinspires.ftc.teamcode.Autonomous.Camera;

import android.graphics.Bitmap;

import com.google.zxing.Result;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

//@Autonomous
@Disabled
public class WebcamCapture {
    OpenCvWebcam webcam;

    public void initialize(HardwareMap hardwareMap) {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        webcam.setPipeline(new SamplePipeline());

        webcam.setMillisecondsPermissionTimeout(5000); // Timeout for obtaining permission is configurable. Set before opening.
        //В джаве пиздец какие колхозные анонимные функции, в шарпе всё в тысячу раз чище.
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {

                webcam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        webcam.stopStreaming();
    }


    class SamplePipeline extends OpenCvPipeline {
        boolean viewportPaused;
        @Override
        public Mat processFrame(Mat input) {
            Result result;
            Bitmap bitmap = Bitmap.createBitmap(input.cols(), input.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(input, bitmap);
            result = ReadQRCode.readQRCode(bitmap);
            return input;
        }

        @Override
        public void onViewportTapped() {
            viewportPaused = !viewportPaused;
            if (viewportPaused) {
                webcam.pauseViewport();
            } else {
                webcam.resumeViewport();
            }
        }
    }
}