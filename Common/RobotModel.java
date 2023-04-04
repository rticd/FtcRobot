package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    Coordinates parkingCoordinates;
    public Coordinates getParkingCoordinates() {
        return parkingCoordinates;
    }
    public void setParkingCoordinates(Coordinates parkingCoordinates) {
        this.parkingCoordinates = parkingCoordinates;
    }

    public RobotModel(HardwareMap hardwareMap, Coordinates coordinates, double absAngle,
                      boolean grabberOpen) {
        this.driveComponent = new DriveComponent(hardwareMap);
        this.armComponent = new ArmComponent(hardwareMap);
        this.sensorComponent = new SensorComponent(hardwareMap);

        this.robotHeight = 38.3;
        this.robotWidth = 33;
        this.coordinates = coordinates;
        this.absAngle = absAngle;
        this.armPosition = 0;
        this.grabberOpen = grabberOpen;
    }
}
