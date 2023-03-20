package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

public class MoveHorizontallyAction extends BaseAction {
    double displ;
    RobotModel model;
    DriveComponent driveComponent;

    double prevTicks;
    double power = 1;

    Coordinates initialCoordinates;

    public MoveHorizontallyAction(RobotModel model, DriveComponent driveComponent, double power, double displ, Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.driveComponent = driveComponent;
        this.power = power;
        this.displ = displ;
    }
    @Override
    public void start() {

        if(!finished) {
            //sets initial position to 0
            driveComponent.upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            driveComponent.lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Calculating the powers
            driveComponent.lowerLeft.setPower(power);
            driveComponent.upperRight.setPower(power);
            driveComponent.upperLeft.setPower(power);
            driveComponent.lowerRight.setPower(power);


            int ticksToPosition = 0;
<<<<<<< Updated upstream:Autonomous/Actions/MoveForDistanceAction.java
            if (this.dir == Direction.Y){
                ticksToPosition= -(int)(driveComponent.TICKS_PER_CM * this.displ);
                driveComponent.lowerRight.setTargetPosition(ticksToPosition);
                driveComponent.upperRight.setTargetPosition(ticksToPosition);
                driveComponent.lowerLeft.setTargetPosition(ticksToPosition);
                driveComponent.upperLeft.setTargetPosition(ticksToPosition);
            } else if (this.dir == Direction.X){
                ticksToPosition= (int)(driveComponent.TICKS_PER_CM * this.displ);
                driveComponent.lowerRight.setTargetPosition(ticksToPosition);
                driveComponent.upperRight.setTargetPosition(-ticksToPosition);
                driveComponent.lowerLeft.setTargetPosition(ticksToPosition);
                driveComponent.upperLeft.setTargetPosition(-ticksToPosition);
            }
=======
            ticksToPosition= -(int)(driveComponent.TICKS_PER_CM * this.displ);
            driveComponent.lowerRight.setTargetPosition(ticksToPosition);
            driveComponent.upperRight.setTargetPosition(-ticksToPosition);
            driveComponent.lowerLeft.setTargetPosition(-ticksToPosition);
            driveComponent.upperLeft.setTargetPosition(ticksToPosition);

>>>>>>> Stashed changes:Autonomous/Actions/MoveHorizontallyAction.java
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
            telemetry.addData("Coordinates x", model.coordinates.getX());
            telemetry.addData("Coordinates y", model.coordinates.getY());
            telemetry.addData("abs angle", model.absAngle);
            //updating model
<<<<<<< Updated upstream:Autonomous/Actions/MoveForDistanceAction.java
            double absAngleInRads = model.absAngle*Math.PI/180;
            /*
            double x = cmTraveled * Math.cos(absAngleInRads);
            double y = cmTraveled * Math.sin(absAngleInRads);
            */
=======
>>>>>>> Stashed changes:Autonomous/Actions/MoveHorizontallyAction.java
            Coordinates vector = new Coordinates(0,0);
            vector = new Coordinates(cmTraveled*Math.cos(model.absAngle), cmTraveled*Math.sin(model.absAngle));
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
