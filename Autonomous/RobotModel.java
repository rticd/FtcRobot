package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotModel {
    //Robot components
    DriveComponent driveComponent;
    public DriveComponent getDriveComponent() {
        return driveComponent;
    }

    ArmComponent armComponent;
    public ArmComponent getArmComponent() {
        return armComponent;
    }

    //absolute coordinates on the field
    public Coordinates coordinates;
    public double robotHeight;
    public double robotWidth;

    //angle to the x axis
    public double absAngle;
    //current arm position
    public ArmPosition armPosition;
    //current grabber state
    public boolean grabberOpen;

    public RobotModel(DriveComponent driveComponent, ArmComponent armComponent, Coordinates coordinates, double absAngle, boolean grabberOpen) {
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.robotHeight = 38.3;
        this.robotWidth = 33;
        this.coordinates = coordinates;
        this.absAngle = absAngle;
        this.armPosition = ArmPosition.Zero;
        this.grabberOpen = grabberOpen;
    }
}
