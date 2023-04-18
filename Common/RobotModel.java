package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Common.Components.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Components.CameraComponent;
import org.firstinspires.ftc.teamcode.Common.Components.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Components.SensorComponent;

public class RobotModel {
    //Robot components
    CameraComponent cameraComponent;
    public CameraComponent getCameraComponent() {
        return cameraComponent;
    }
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
    public boolean grabberOpen; //for manual

    public boolean withCone;
    Coordinates parkingCoordinates;

    public boolean vibrated;


    public Coordinates getParkingCoordinates() {
        return parkingCoordinates;
    }
    public void setParkingCoordinates(Coordinates parkingCoordinates) {
        this.parkingCoordinates = parkingCoordinates;
    }

    public RobotModel(HardwareMap hardwareMap) {
        this.driveComponent = new DriveComponent(hardwareMap);
        this.armComponent = new ArmComponent(hardwareMap);
        this.sensorComponent = new SensorComponent(hardwareMap);
        this.cameraComponent = new CameraComponent(hardwareMap,640,480);
        this.robotHeight = 38.3;
        this.robotWidth = 33;
        this.armPosition = 0;
        coordinates = new Coordinates(0,0);
        absAngle = 0;
    }
}
