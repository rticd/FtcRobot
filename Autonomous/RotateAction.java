package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.DriveComponent;

//Не законченно
public class RotateAction implements IAction
{
    RobotModel model;

    DriveComponent driveComponent;

    double rotationAngle;
    public double getRotationAngle() {
        return rotationAngle;
    }

    boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }

    public RotateAction(RobotModel model, DriveComponent driveComponent, double rotationAngle) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.rotationAngle = rotationAngle;
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }


}
