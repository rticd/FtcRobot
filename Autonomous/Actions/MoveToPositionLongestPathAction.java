package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveToPositionLongestPathAction extends BaseAction {
    public Telemetry telemetry;
    double delta;
    double abs;
    RobotModel model;

    DriveComponent driveComponent;

    Coordinates targetCoordinates;
    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    ActionQueue actionQueueForX;
    ActionQueue actionQueueForY;
    boolean startedY = false;

    public MoveToPositionLongestPathAction(RobotModel model, DriveComponent driveComponent,
                                           Coordinates targetCoordinates) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.targetCoordinates = targetCoordinates;
        actionQueueForX = new ActionQueue();
        actionQueueForY = new ActionQueue();
    }

    @Override
    public void start() {
        if(finished) return;
        xAxisPhase();
    }

    //Sorry for such a messy solution. In don't know how to do it without coroutines.
    @Override
    public void update() {
        if(finished) return;
        telemetry.addData("model abs angle:", model.absAngle);
        if(actionQueueForY.currentAction != null) {
            telemetry.addData("y action:", actionQueueForY.currentAction);
            telemetry.addData("y action finished:", actionQueueForY.currentAction.isFinished());
            telemetry.addData("y delta angle:", delta);
            telemetry.addData("y abs angle:", abs);
            telemetry.addData(" abs - yabs angle:", model.absAngle - abs);

        }

        if(actionQueueForX.isFinished() && !startedY)
            yAxisPhase();
        if(actionQueueForX.isFinished() && actionQueueForY.isFinished())
            finished = true;

        if(!startedY)
            actionQueueForX.update();
        else
            actionQueueForY.update();
    }

    void xAxisPhase() {
        Coordinates vector = new Coordinates(
                targetCoordinates.getX() - model.coordinates.getX(),
                0
        );

        double absAngle = 0;
        if(targetCoordinates.getX() > 0)
            absAngle = 180;
        if(targetCoordinates.getX() < 0)
            absAngle = 0;

        double deltaAngle = absAngle - model.absAngle;
        BaseAction rotateAction = new RotateAction(model, driveComponent, deltaAngle);
        rotateAction.setTelemetry(telemetry);
        actionQueueForX.setNextAction(rotateAction);
        BaseAction driveAction = new MoveForDistanceAction(model, driveComponent, vector.getX());
        actionQueueForX.setNextAction(driveAction);
        actionQueueForX.start();
    }

    void yAxisPhase() {
        startedY = true;
        Coordinates vector = new Coordinates(
                0,
                model.coordinates.getY() - targetCoordinates.getY()
        );
        double absAngle = 0;
        if(targetCoordinates.getY() > 0)
            absAngle = 90;
        if(targetCoordinates.getY() < 0)
            absAngle = 270;

        double deltaAngle = absAngle - model.absAngle;
        delta = deltaAngle;
        abs = absAngle;
        BaseAction rotateAction = new RotateAction(model, driveComponent, deltaAngle);
        rotateAction.setTelemetry(telemetry);
        actionQueueForY.setNextAction(rotateAction);
        IAction driveAction = new MoveForDistanceAction(model, driveComponent, vector.getY());
        actionQueueForY.setNextAction(driveAction);
        actionQueueForY.start();

    }
}
