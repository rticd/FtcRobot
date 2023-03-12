package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

//This is a composite action. It used other actions to achieve it's goals.
//Don't see any reasons do differentiate between them yet.
public class MoveToPositionShortestPathAction extends BaseAction {
    double offset;
    RobotModel model;

    DriveComponent driveComponent;

    Coordinates targetCoordinates;
    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    ActionQueue actionQueue;

    public MoveToPositionShortestPathAction(RobotModel model, DriveComponent driveComponent,
                                            Coordinates targetCoordinates, double offset) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.targetCoordinates = targetCoordinates;
        this.offset = offset;
        actionQueue = new ActionQueue();
    }


    @Override
    public void start() {
        if(finished) return;
        constructActionQueue();
    }

    @Override
    public void update() {
        if(finished) return;
        actionQueue.update();
        if(actionQueue.isFinished())
            finished = true;

    }

    void constructActionQueue() {

        Coordinates vector = new Coordinates(
                targetCoordinates.getX() - model.coordinates.getX(),
                targetCoordinates.getY() - model.coordinates.getY()
        );
        double distanceToMove = Math.sqrt(
                vector.getX()*vector.getX() + vector.getY()*vector.getY()
        ) - offset;
        double absAngle = Math.atan2(targetCoordinates.getY(), targetCoordinates.getX()) * 180 / Math.PI;
        RotateAction rotateAction = new RotateAction(model, driveComponent, absAngle);
        rotateAction.setTelemetry(telemetry);
        actionQueue.setNextAction(rotateAction);

        MoveForDistanceAction driveAction = new MoveForDistanceAction(model, driveComponent, distanceToMove);
        driveAction.setTelemetry(telemetry);
        actionQueue.setNextAction(driveAction);

        actionQueue.start();
    }
}
