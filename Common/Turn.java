package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Common.RotationDirection;

public class Turn {
    public static double offset = 0;
    public static double targetAngle = 0;

    public static boolean isTurning = false;

    public static RotationDirection turn(BNO055IMU imu, double angle, Telemetry telemetry, DriveComponent driveComponent) {

        if (!isTurning) {
            double angleY = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
            targetAngle = angleY + angle;
            isTurning = true;
        }

        double currentAngle = -imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        telemetry.addData("Target Angle", targetAngle);
        telemetry.addData("Current Angle", currentAngle);
        double power = getPowerLevel(currentAngle,targetAngle,angle);
        int direction = (int) Math.signum(targetAngle - currentAngle);
        driveComponent.lowerLeft.setPower(-direction * power);
        driveComponent.upperRight.setPower(direction * power);
        driveComponent.upperLeft.setPower(-direction * power);
        driveComponent.lowerRight.setPower(direction * power);

        driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (Math.abs(currentAngle - targetAngle) <= 0.5 || Math.abs(currentAngle - targetAngle) >180.5) {
            driveComponent.lowerLeft.setPower(0);
            driveComponent.upperRight.setPower(0);
            driveComponent.upperLeft.setPower(0);
            driveComponent.lowerRight.setPower(0);
            isTurning = false;
            return RotationDirection.none;
        }else {
            return direction == 1 ? RotationDirection.right : RotationDirection.left;
        }
    }
    public static double getPowerLevel(double currentAngle, double targetAngle, double angle ) {
        double maxPower = 1.0;
        double minPower = 0.4;
        double angleRange = Math.abs(angle)/2; // The angle range over which power level decreases

        // Calculate the absolute difference between the current angle and the target angle
        double angleDiff = Math.abs(currentAngle - targetAngle);

        // Calculate the power level based on the angle difference
        double powerLevel = minPower + (angleDiff / angleRange) * (maxPower - minPower);

        // Make sure the power level is within the valid range of 0.5 to 1.0
        powerLevel = Math.max(powerLevel, minPower);
        powerLevel = Math.min(powerLevel, maxPower);

        return -powerLevel;
    }
}
