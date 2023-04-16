package org.firstinspires.ftc.teamcode.Common;

import com.qualcomm.robotcore.hardware.HardwareMap;

<<<<<<< Updated upstream
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
=======
import org.firstinspires.ftc.teamcode.Autonomous.AutoBlue;
import org.firstinspires.ftc.teamcode.Common.Component.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Component.CameraComponent;
import org.firstinspires.ftc.teamcode.Common.Component.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Component.SensorComponent;
>>>>>>> Stashed changes

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
    public boolean grabberOpen;
    Coordinates parkingCoordinates;
<<<<<<< Updated upstream
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
=======
    public int parkingPosition;

    public boolean withCone = false;


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
        this.cameraComponent = new CameraComponent(hardwareMap, AutoBlue.dashboard,640,480);
        this.robotHeight = 38.3;
        this.robotWidth = 33;
>>>>>>> Stashed changes
        this.armPosition = 0;

    }
}
