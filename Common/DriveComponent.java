package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.teamcode.Manual.Distance;

public class DriveComponent {
    public final double WHEEL_DIAMETER = 7.5;
    public final int TICKS_PER_REVOLUTION = 288;
    public final int TICKS_PER_CM = (int)(TICKS_PER_REVOLUTION/(WHEEL_DIAMETER * Math.PI));
    public final double DEGREES_PER_CM_OF_ROTATION = 1.65;
    public DcMotor upperLeft;
    public DcMotor upperRight;
    public DcMotor lowerLeft;
    public DcMotor lowerRight;
    public BNO055IMU imu;

    public  DistanceSensor sensorRange;

    public DriveComponent(DcMotor upperLeft, DcMotor lowerLeft, DcMotor upperRight, DcMotor lowerRight,
                          BNO055IMU imu, DistanceSensor sensorRange) {
        lowerRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.lowerRight = lowerRight;
        this.imu = imu;
        this.sensorRange = sensorRange;

    }
}
