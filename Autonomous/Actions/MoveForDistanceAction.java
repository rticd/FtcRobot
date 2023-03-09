package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveForDistanceAction extends BaseAction {
    RobotModel model;

    DriveComponent driveComponent;

    double distance;
    public double getDistance() {
        return distance;
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

            driveComponent.lowerRight.setPower(1);
            driveComponent.upperRight.setPower(1);
            driveComponent.lowerLeft.setPower(1);
            driveComponent.upperLeft.setPower(1);

            //sets new position
            int ticksToPosition = (int)(driveComponent.TICKS_PER_CM * distance);

            driveComponent.lowerRight.setTargetPosition(-ticksToPosition);
            driveComponent.upperRight.setTargetPosition(-ticksToPosition);
            driveComponent.lowerLeft.setTargetPosition(-ticksToPosition);
            driveComponent.upperLeft.setTargetPosition(-ticksToPosition);

            driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        }
    }

    @Override
    public void update() {
        if(!finished) {
            int targetTicks = (int)(driveComponent.TICKS_PER_CM * distance);
            int currentTicks = driveComponent.upperLeft.getCurrentPosition();
            float cmTraveled = currentTicks / driveComponent.TICKS_PER_CM;

            //updating model
            double x = cmTraveled * Math.cos(model.absAngle);
            double y = cmTraveled * Math.sin(model.absAngle);
            Coordinates vector = new Coordinates(x, y);
            model.coordinates = Coordinates.add(model.coordinates, vector);

            //checking if finished
            if(targetTicks == currentTicks) {
                finished = true;
            }
        }

    }


}
