package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;

public class DriveComponent {
    public final double WHEEL_DIAMETER = 7.5;
    public final int TICKS_PER_REVOLUTION = 288;
    public final int TICKS_PER_CM = (int)(TICKS_PER_REVOLUTION/(WHEEL_DIAMETER * Math.PI));
    public final double DEGREES_PER_CM_OF_ROTATION = 1.65;
    public DcMotor upperLeft;
    public DcMotor upperRight;
    public DcMotor lowerLeft;
    public DcMotor lowerRight;
    public GyroSensor gyroSensor;

    public DriveComponent(DcMotor upperLeft, DcMotor lowerLeft, DcMotor upperRight, DcMotor lowerRight,
                          GyroSensor gyroSensor) {
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.lowerRight = lowerRight;
        this.gyroSensor = gyroSensor;
        lowerRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        upperRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
