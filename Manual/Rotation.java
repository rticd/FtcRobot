package org.firstinspires.ftc.teamcode.Manual;

import android.view.ViewDebug;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Turn;
import org.firstinspires.ftc.teamcode.Common.RotationDirection;

@TeleOp
@Disabled
public class Rotation extends LinearOpMode {

    BNO055IMU imu;
    Orientation angles;
    DriveComponent driveComponent;

    double power = 1;
    RotationDirection rot  = RotationDirection.none;
    @Override
    public void runOpMode() throws InterruptedException {
        initialize(hardwareMap);

        while(true && opModeIsActive()) {

            turn();
            telemetry.update();

        }
    }

    void initialize(HardwareMap hardwareMap) {


        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");


        //gyro sensor
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "Calibration.json";
        imu = hardwareMap.get(BNO055IMU.class, "gyro");
        imu.initialize(parameters);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight, imu,null);
        waitForStart();
    }

    void turn(){

        if (gamepad1.left_bumper && rot==RotationDirection.none){
            rot=Turn.turn(imu, -90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        } else if (gamepad1.right_bumper && rot==RotationDirection.none){
            rot=Turn.turn(imu,90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        }
        if(rot==RotationDirection.left){
            rot=Turn.turn(imu,  -90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        } else if (rot==RotationDirection.right) {
            rot=Turn.turn(imu, 90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        }

    }
}
