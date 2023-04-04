package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

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

    public DriveComponent(HardwareMap hardwareMap) {
        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");

        lowerRight.setDirection(DcMotorSimple.Direction.REVERSE);
        lowerLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.lowerRight = lowerRight;

    }
}
