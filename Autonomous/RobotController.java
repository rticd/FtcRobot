package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.ActionQueue;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveHorizontallyAction;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class RobotController {
    Telemetry telemetry;
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    RobotModel model;
    FieldModel fieldModel;
    DriveComponent driveComponent;
    ArmComponent armComponent;
    ActionQueue actionQueue;

    public RobotController(RobotModel model, FieldModel fieldModel, DriveComponent driveComponent, ArmComponent armComponent, Telemetry telemetry) {
        this.model = model;
        this.fieldModel = fieldModel;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.telemetry = telemetry;
        constructActionSequence();
    }

    void constructActionSequence() {
        actionQueue = new ActionQueue();
        IAction movementAction = new MoveHorizontallyAction(model, driveComponent, 0.1, 50, telemetry);
        actionQueue.setNextAction(movementAction);
    }

    public void start() {
        if(!actionQueue.isFinished())
            actionQueue.start();
    }
    public void update() {
        if(actionQueue.getCurrentAction() != null) {
             telemetry.addData("current action: ", actionQueue.getCurrentAction());
             telemetry.addData("current finished: ", actionQueue.getCurrentAction().isFinished());
        }
        if(!actionQueue.isFinished()) {
            actionQueue.update();
        }
    }
}
