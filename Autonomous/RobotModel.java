package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

public class RobotModel {
    //absolute coordinates on the field
    public Coordinates coordinates;
    //angle to the x axis
    public double angle;
    //current arm position
    public ArmPosition armPosition;
    //current grabber state
    public boolean grabberOpen;

    public RobotModel(Coordinates coordinates, double angle, ArmPosition armPosition, boolean grabberOpen) {
        this.coordinates = coordinates;
        this.angle = angle;
        this.armPosition = armPosition;
        this.grabberOpen = grabberOpen;
    }
}
