package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;

public class DriveComponent {
    //Circumference of a mecanum wheel is difficult to calculate
    public final int TICKS_PER_CM = (int)(288/(8 * Math.PI));
    public final double DEGREES_PER_CM_OF_ROTATION = 1.6;
    public DcMotor upperLeft;
    public DcMotor upperRight;
    public DcMotor lowerLeft;
    public DcMotor lowerRight;

    public DriveComponent(DcMotor upperLeft, DcMotor lowerLeft, DcMotor upperRight, DcMotor lowerRight) {
        this.upperLeft = upperLeft;
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
        this.lowerRight = lowerRight;
        upperLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        upperRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
