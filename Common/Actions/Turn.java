package org.firstinspires.ftc.teamcode.Common.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Autonomous.Action;
import org.firstinspires.ftc.teamcode.Common.Component.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Component.SensorComponent;

public class Turn extends BaseAction {
    final double MAX_POWER = 1;
    final double MIN_POWER = 0.4;
    DriveComponent driveComponent;
    SensorComponent sensorComponent;
    double deltaAngle;
    double targetAngle;

    public boolean turning = false;

    public RotationDirection rotationDirection;
    public Turn(RobotModel model, double deltaAngle, Telemetry telemetry) {
        super(model, telemetry);
        driveComponent = model.getDriveComponent();
        sensorComponent = model.getSensorComponent();
        this.deltaAngle = deltaAngle;
        this.rotationDirection = deltaAngle >= 0 ? RotationDirection.right : RotationDirection.left;
    }

    @Override
    public void start() {
        double angleY = -sensorComponent.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        targetAngle = angleY + deltaAngle;
        turning = true;

    }

    @Override
    public void update() {
        double currentAngle = -sensorComponent.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        telemetry.addData("Target Angle", targetAngle);
        telemetry.addData("Current Angle", currentAngle);
        telemetry.update();
        double power = getPowerLevel(currentAngle, targetAngle, Math.abs(deltaAngle));
        int direction = (int) Math.signum(targetAngle - currentAngle);
        driveComponent.lowerLeft.setPower(-direction * power);
        driveComponent.upperRight.setPower(direction * power);
        driveComponent.upperLeft.setPower(-direction * power);
        driveComponent.lowerRight.setPower(direction * power);
        driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        if (Math.abs(currentAngle - targetAngle) <= 0.5 || Math.abs(currentAngle - targetAngle) > 180.5) {
            driveComponent.lowerLeft.setPower(0);
            driveComponent.upperRight.setPower(0);
            driveComponent.upperLeft.setPower(0);
            driveComponent.lowerRight.setPower(0);
            rotationDirection = RotationDirection.none;
            Action.rotate = null;
        } else{
            rotationDirection = direction == 1 ? RotationDirection.right : RotationDirection.left;
        }
    }
    double getPowerLevel(double currentAngle, double targetAngle, double angle ) {
        double angleRange = Math.abs(angle)/2; // The angle range over which power level decreases

        // Calculate the absolute difference between the current angle and the target angle
        double angleDiff = Math.abs(currentAngle - targetAngle);

        // Calculate the power level based on the angle difference
        double powerLevel = MIN_POWER + (angleDiff / angleRange) * (MAX_POWER - MIN_POWER);

        // Make sure the power level is within the valid range of 0.5 to 1.0
        powerLevel = Math.max(powerLevel, MIN_POWER);
        powerLevel = Math.min(powerLevel, MAX_POWER);

        return -powerLevel;
    }

}
