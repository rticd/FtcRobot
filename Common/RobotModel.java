package org.firstinspires.ftc.teamcode.Common;

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

    SensorComponent sensorComponent;
    public SensorComponent getSensorComponent(){
        return sensorComponent;
    }
    //absolute coordinates on the field
    public Coordinates coordinates;
    public double robotHeight;
    public double robotWidth;

    //angle to the x axis
    public double absAngle;
    //current arm position
    public double armPosition;
    //current grabber state
    public boolean grabberOpen;

    public RobotModel(DriveComponent driveComponent, ArmComponent armComponent,SensorComponent sensorComponent, Coordinates coordinates, double absAngle, boolean grabberOpen) {
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.robotHeight = 38.3;
        this.robotWidth = 33;
        this.coordinates = coordinates;
        this.sensorComponent = sensorComponent;
        this.absAngle = absAngle;
        this.armPosition = 0;
        this.grabberOpen = grabberOpen;
    }
}
