package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.Coordinates;

public class MoveHorizontallyAction extends BaseAction {
    double displacement;
    RobotModel model;

    double prevTicks;
    double power = 1;

    Coordinates initialCoordinates;

    public MoveHorizontallyAction(RobotModel model, double power, double displacement, Telemetry telemetry) {
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
            model.getDriveComponent().lowerRight.setTargetPosition(-ticksToPosition);
            model.getDriveComponent().upperRight.setTargetPosition(-ticksToPosition);
            model.getDriveComponent().lowerLeft.setTargetPosition(ticksToPosition);
            model.getDriveComponent().upperLeft.setTargetPosition(-ticksToPosition);

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
            float cmTraveled = currentTicks * model.getDriveComponent().TICKS_PER_CM;
            //updating model
            Coordinates vector = new Coordinates(0, 0);
            //Horizontal vector is perpendicular to the vertical, so +90 degrees.
            vector = new Coordinates(cmTraveled * Math.cos(model.absAngle + Math.PI/2), cmTraveled * Math.sin(model.absAngle + Math.PI/2));
            Coordinates targetCoordinates = Coordinates.add(initialCoordinates, vector);
            model.coordinates = targetCoordinates;
            telemetry.addData("targetCoordinates X:", Math.cos(model.absAngle + Math.PI/2));
            telemetry.addData("targetCoordinates Y:", Math.sin(model.absAngle + Math.PI/2));
            telemetry.addData("currentCoordinates Y:", model.coordinates.getY());
            telemetry.addData("currentCoordinates X:", model.coordinates.getY());

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
