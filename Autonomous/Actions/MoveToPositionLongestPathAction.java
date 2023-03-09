package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveToPositionLongestPathAction extends BaseAction {
    RobotModel model;

    DriveComponent driveComponent;

    ArmComponent armComponent;

    Coordinates targetCoordinates;
    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    IAction xAxisRotation;
    IAction xAxisDrive;
    IAction yAxisRotation;
    IAction yAxisDrive;

    boolean isFirstPhase;

    public MoveToPositionLongestPathAction(RobotModel model, DriveComponent driveComponent,
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
        xAxisPhase();
    }

    //Sorry for such a messy solution. In don't know how to do it without coroutines or tasks.
    @Override
    public void update() {
        if(finished) return;

        //Wow this is terrible
        if(isFirstPhase == true) {
            xAxisRotation.update();
            xAxisDrive.update();
            if(xAxisRotation.isFinished())
                xAxisDrive.start();
            if(xAxisDrive.isFinished())
                isFirstPhase = false;

        } else {
            yAxisRotation.update();
            yAxisDrive.update();
            if(yAxisRotation.isFinished())
                yAxisDrive.start();
            if(yAxisDrive.isFinished())
                finished = true;
        }
    }

    void xAxisPhase() {
        Coordinates vector = new Coordinates(
                model.coordinates.getX() - targetCoordinates.getX(),
                model.coordinates.getY() - targetCoordinates.getY()
        );

        double absAngle = 0;
        if(targetCoordinates.getX() > 0)
            absAngle = 180;
        if(targetCoordinates.getX() < 0)
            absAngle = 0;

        double deltaAngle = model.absAngle - absAngle;
        xAxisRotation = new RotateAction(model, driveComponent, deltaAngle);
        xAxisRotation.start();
        xAxisDrive = new MoveForDistanceAction(model, driveComponent, vector.getX());
    }

    void yAxisPhase() {
        Coordinates vector = new Coordinates(
                model.coordinates.getX() - targetCoordinates.getX(),
                model.coordinates.getY() - targetCoordinates.getY()
        );
        double absAngle = 0;
        if(targetCoordinates.getY() > 0)
            absAngle = 90;
        if(targetCoordinates.getY() < 0)
            absAngle = 270;

        double deltaAngle = model.absAngle - absAngle;
        yAxisRotation = new RotateAction(model, driveComponent, deltaAngle);
        yAxisRotation.start();
        yAxisDrive = new MoveForDistanceAction(model, driveComponent, vector.getY());
    }
}
