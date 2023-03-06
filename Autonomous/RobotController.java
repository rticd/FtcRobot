package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotController {
    RobotModel model;
    DriveComponent driveComponent;
    ArmComponent armComponent;

    public RobotController(RobotModel model, DriveComponent driveComponent, ArmComponent armComponent) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
    }

    public void update() {

    }
}
