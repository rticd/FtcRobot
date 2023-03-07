package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveForDistanceAction implements IAction {

    RobotModel model;

    DriveComponent driveComponent;

    double distance;
    public double getDistance() {
        return distance;
    }

    boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }

    public MoveForDistanceAction(RobotModel model, DriveComponent driveComponent, double distance) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.distance = distance;
    }

    @Override
    public void start() {
        if(!finished) {
            //sets initial position to 0
            driveComponent.upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            //sets new position
            int ticksToPosition = (int)(driveComponent.TICKS_PER_CM * distance);
            driveComponent.lowerRight.setTargetPosition(ticksToPosition);
            driveComponent.upperRight.setTargetPosition(ticksToPosition);
            driveComponent.lowerLeft.setTargetPosition(ticksToPosition);
            driveComponent.upperLeft.setTargetPosition(ticksToPosition);
        }
    }

    @Override
    public void update() {
        if(!finished) {
            int targetTicks = (int)(driveComponent.TICKS_PER_CM * distance);
            int currentTicks = driveComponent.upperLeft.getCurrentPosition();
            float cmTraveled = currentTicks / driveComponent.TICKS_PER_CM;

            //updating model
            double x = cmTraveled * Math.cos(model.angle);
            double y = cmTraveled * Math.sin(model.angle);
            Coordinates vector = new Coordinates(x, y);
            model.coordinates = Coordinates.add(model.coordinates, vector);

            //checking if finished
            if(targetTicks == currentTicks) {
                finished = true;
            }
        }

    }


}
