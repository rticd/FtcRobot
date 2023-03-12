package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveToPositionLongestPathAction extends BaseAction {
    double delta;
    double abs;
    RobotModel model;

    DriveComponent driveComponent;

    Coordinates targetCoordinates;
    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    ActionQueue actionQueue;
    boolean startedY = false;

    public MoveToPositionLongestPathAction(RobotModel model, DriveComponent driveComponent,
                                           Coordinates targetCoordinates) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.targetCoordinates = targetCoordinates;

    }

    @Override
    public void start() {
        if(finished) return;
        xAxisPhase();
    }
    @Override
    public void update() {
        if(finished) return;

        if(actionQueue.isFinished() && !startedY)
            yAxisPhase();
        else if(actionQueue.isFinished() && startedY)
            finished = true;
        actionQueue.update();
    }

    void xAxisPhase() {
        Coordinates vector = new Coordinates(
                targetCoordinates.getX() - model.coordinates.getX(),
                0
        );

        double absAngle = 0;
        if(targetCoordinates.getX() > 0)
            absAngle = 0;
        if(targetCoordinates.getX() < 0)
            absAngle = 180;

        actionQueue = new ActionQueue();
        BaseAction rotateAction = new RotateAction(model, driveComponent, absAngle);
        rotateAction.setTelemetry(telemetry);
        actionQueue.setNextAction(rotateAction);

        BaseAction driveAction = new MoveForDistanceAction(model, driveComponent, vector.getX());
        actionQueue.setNextAction(driveAction);
        driveAction.setTelemetry(telemetry);
        actionQueue.start();
    }

    void yAxisPhase() {
        Coordinates vector = new Coordinates(
                0,
                targetCoordinates.getY() - model.coordinates.getY()
        );
        double absAngle = 0;
        if(targetCoordinates.getY() > 0)
            absAngle = 90;
        if(targetCoordinates.getY() < 0)
            absAngle = 270;

        actionQueue = new ActionQueue();
        BaseAction rotateAction = new RotateAction(model, driveComponent, absAngle);
        rotateAction.setTelemetry(telemetry);
        actionQueue.setNextAction(rotateAction);

        IAction driveAction = new MoveForDistanceAction(model, driveComponent, vector.getY());
        driveAction.setTelemetry(telemetry);
        actionQueue.setNextAction(driveAction);
        actionQueue.start();
        startedY = true;
    }
}
