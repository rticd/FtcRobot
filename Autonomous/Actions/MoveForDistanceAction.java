package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Direction;

public class MoveForDistanceAction extends BaseAction {
    double displ;
    RobotModel model;
    DriveComponent driveComponent;

    double prevTicks;
    double robot_power = 1;

    Direction dir;

    Coordinates initialCoordinates;
    //public double getDistance() {
        //return distance;
    //}

    public MoveForDistanceAction(RobotModel model, DriveComponent driveComponent, double robot_power, double displ, Direction dir, Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.driveComponent = driveComponent;
        this.robot_power = robot_power;
        this.displ = displ;
        this.dir = dir;
    }

    @Override
    public void start() {

        if(!finished) {
            //sets initial position to 0
            driveComponent.upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            driveComponent.lowerRight.setPower(robot_power);
            driveComponent.upperRight.setPower(robot_power);
            driveComponent.lowerLeft.setPower(robot_power);
            driveComponent.upperLeft.setPower(robot_power);

            int ticksToPosition = 0;
            if (this.dir == Direction.Y){
                ticksToPosition= -(int)(driveComponent.TICKS_PER_CM * this.displ);
                driveComponent.lowerRight.setTargetPosition(ticksToPosition);
                driveComponent.upperRight.setTargetPosition(ticksToPosition);
                driveComponent.lowerLeft.setTargetPosition(ticksToPosition);
                driveComponent.upperLeft.setTargetPosition(ticksToPosition);
            } else if (this.dir == Direction.X){
                ticksToPosition= (int)(driveComponent.TICKS_PER_CM * this.displ);
                driveComponent.lowerRight.setTargetPosition(-ticksToPosition);
                driveComponent.upperRight.setTargetPosition(ticksToPosition);
                driveComponent.lowerLeft.setTargetPosition(ticksToPosition);
                driveComponent.upperLeft.setTargetPosition(-ticksToPosition);
            }
            //sets new position
            driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            initialCoordinates = new Coordinates(model.coordinates.getX(), model.coordinates.getY());
        }
    }

    @Override
    public void update() {
        if(!finished) {
            int targetTicks = -(int)(driveComponent.TICKS_PER_CM * displ);
            int currentTicks = -driveComponent.upperLeft.getCurrentPosition();
            float cmTraveled = currentTicks / driveComponent.TICKS_PER_CM;

            //updating model
            double absAngleInRads = model.absAngle*Math.PI/180;
            Coordinates vector = new Coordinates(0,0);
            if (this.dir == Direction.Y){
                vector = new Coordinates(-cmTraveled*Math.sin(model.absAngle), cmTraveled*Math.cos(model.absAngle));
            } else {
                vector = new Coordinates(cmTraveled*Math.cos(model.absAngle), cmTraveled*Math.sin(model.absAngle));
            }
            model.coordinates = Coordinates.add(initialCoordinates, vector);
            //checking if finished
            if(targetTicks == currentTicks && currentTicks == prevTicks) {
                finished = true;
            }
            prevTicks = currentTicks;
        }
    }

    @Override
    public void exit() {

    }
}
