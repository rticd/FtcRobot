package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

//This is a composite action. It used other actions to achieve it's goals.
//Don't see any reasons do differentiate between them yet.
public class MoveToPositionShortestPathAction extends BaseAction {

    RobotModel model;

    DriveComponent driveComponent;

    ArmComponent armComponent;

    Coordinates targetCoordinates;
    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    IAction rotateAction;
    IAction driveAction;

    public MoveToPositionShortestPathAction(RobotModel model, DriveComponent driveComponent,
                                            Coordinates targetCoordinates) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.armComponent = armComponent;
        this.targetCoordinates = targetCoordinates;
    }


    //No async await :(
    @Override
    public void start() {
        if(finished) return;
        rotationPhase();
    }

    //Sorry for such a messy solution. In don't know how to do it without coroutines or tasks.
    @Override
    public void update() {
        if(finished) return;
        if(driveAction != null) {
            driveAction.update();
            if(driveAction.isFinished())
                finished = true;
        } else if(rotateAction != null) {
            rotateAction.update();
            if(rotateAction.isFinished())
                drivingPhase();
        }
    }

    void rotationPhase() {
        Coordinates vector = new Coordinates(
                model.coordinates.getX() - targetCoordinates.getX(),
                model.coordinates.getY() - targetCoordinates.getY()
        );
        double absAngle = Math.atan2(targetCoordinates.getY(), targetCoordinates.getX()) * 180 / Math.PI;
        double deltaAngle = absAngle - model.absAngle ;
        rotateAction = new RotateAction(model, driveComponent, deltaAngle);
        rotateAction.start();
    }

    void drivingPhase() {
        Coordinates vector = new Coordinates(
                model.coordinates.getX() - targetCoordinates.getX(),
                model.coordinates.getY() - targetCoordinates.getY()
        );
        double distanceToMove = Math.sqrt(
                vector.getX()*vector.getX() + vector.getY()* vector.getY()
        );
        driveAction = new MoveForDistanceAction(model, driveComponent, distanceToMove);
        driveAction.start();
    }
}
