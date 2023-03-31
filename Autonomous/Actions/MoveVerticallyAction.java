package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

public class MoveVerticallyAction extends BaseAction {
    double displacement;
    RobotModel model;

    double prevTicks;
    double power = 1;

    Coordinates initialCoordinates;

    public MoveVerticallyAction(RobotModel model, double power, double displacement, Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.power = power;
        this.displacement = displacement;
    }

    @Override
    public void start() {

        if (!finished) {
            //sets initial position to 0
            model.getDriveComponent().upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Calculating the powers
            model.getDriveComponent().lowerLeft.setPower(power);
            model.getDriveComponent().upperRight.setPower(power);
            model.getDriveComponent().upperLeft.setPower(power);
            model.getDriveComponent().lowerRight.setPower(power);


            int ticksToPosition = 0;
            ticksToPosition = (int) (model.getDriveComponent().TICKS_PER_CM * this.displacement);
            model.getDriveComponent().lowerRight.setTargetPosition(ticksToPosition);
            model.getDriveComponent().upperRight.setTargetPosition(ticksToPosition);
            model.getDriveComponent().lowerLeft.setTargetPosition(ticksToPosition);
            model.getDriveComponent().upperLeft.setTargetPosition(ticksToPosition);

            //sets new position
            model.getDriveComponent().upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            model.getDriveComponent().lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            model.getDriveComponent().upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            model.getDriveComponent().lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            initialCoordinates = new Coordinates(model.coordinates.getX(), model.coordinates.getY());
        }
    }

    @Override
    public void update() {
        if (!finished) {
            int targetTicks = (int) (model.getDriveComponent().TICKS_PER_CM * displacement);
            int currentTicks = model.getDriveComponent().upperLeft.getCurrentPosition();
            float cmTraveled = currentTicks / model.getDriveComponent().TICKS_PER_CM;

            //updating model
            Coordinates vector = new Coordinates(0, 0);
            vector = new Coordinates(cmTraveled * Math.cos(model.absAngle), cmTraveled * Math.sin(model.absAngle));
            model.coordinates = Coordinates.add(initialCoordinates, vector);

            telemetry.addData("currentCoordinates X:", model.coordinates.getX());
            telemetry.addData("currentCoordinates Y:", model.coordinates.getY());
            telemetry.addData("cmTraveled:", cmTraveled);
            telemetry.addData("absAngle", model.absAngle);

            //checking if finished
            if (targetTicks == currentTicks && currentTicks == prevTicks) {
                finished = true;
            }
            prevTicks = currentTicks;
        }
    }

    @Override
    public void exit() {

    }
}