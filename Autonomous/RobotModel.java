package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

public class RobotModel {
    //absolute coordinates on the field
    public Coordinates coordinates;
    public double robotHeight;
    public double robotWidth;

    //angle to the x axis (radians)
    public double absAngle;
    //current arm position
    public ArmPosition armPosition;
    //current grabber state
    public boolean grabberOpen;

    public RobotModel(Coordinates coordinates, double absAngle, ArmPosition armPosition, boolean grabberOpen) {
        this.robotHeight = 38.3;
        this.robotWidth = 33;
        this.coordinates = coordinates;
        this.absAngle = absAngle;
        this.armPosition = armPosition;
        this.grabberOpen = grabberOpen;
    }
}
