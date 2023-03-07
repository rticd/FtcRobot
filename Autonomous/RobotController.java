package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotController {
    Telemetry telemetry;
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    RobotModel model;
    DriveComponent driveComponent;
    ArmComponent armComponent;
    IAction currentAction;

    public RobotController(RobotModel model, DriveComponent driveComponent, ArmComponent armComponent) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        currentAction = new NoAction();
    }

    public void update() {
        currentAction.update();
    }

    public void testMovement() {
        telemetry.addData("inside test movement", true);
        if(currentAction.isFinished()) {
            telemetry.addData("starting movement", true);
            currentAction = new MoveForDistanceAction(model, driveComponent, 10);
            currentAction.start();
        }
    }

    public void testRotation() {
        if(currentAction.isFinished()) {
            currentAction = new RotateAction(model, driveComponent, 90);
            currentAction.start();
        }
    }

    public void testArmControl(ArmPosition armPosition) {
        if(currentAction.isFinished()) {
            telemetry.addData("starting arm position change", true);
            currentAction = new LiftArmAction(model, armComponent, armPosition);
            currentAction.start();

        }
    }

    public void testGraplerOpen() {
        if(currentAction.isFinished()) {
            currentAction = new GrabAction(model, armComponent);
            currentAction.start();
        }
    }

    public void testGraplerClose() {
        if(currentAction.isFinished()) {
            currentAction = new UngrabAction(model, armComponent);
            currentAction.start();
        }
    }
}
