package org.firstinspires.ftc.teamcode.Common.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Actions.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class SetMotion extends BaseAction{
    RobotModel model;
    double power;
    double displacement;
    MotionDirection direction;

    int ticksToPosition;
    public SetMotion(RobotModel model, double power, double displacement, MotionDirection direction, Telemetry telemetry){
        super(model,telemetry);
        this.model = model;
        this.power = power;
        this.displacement=displacement;
        this.direction = direction;
    }
    @Override
    public void start(){
        if (direction == MotionDirection.vertical){
            model.getDriveComponent().upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Calculating the powers
            model.getDriveComponent().lowerLeft.setPower(this.power);
            model.getDriveComponent().upperRight.setPower(this.power);
            model.getDriveComponent().upperLeft.setPower(this.power);
            model.getDriveComponent().lowerRight.setPower(this.power);

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
        } else {
            model.getDriveComponent().upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            model.getDriveComponent().lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Calculating the powers
            model.getDriveComponent().lowerLeft.setPower(-this.power);
            model.getDriveComponent().upperRight.setPower(-this.power);
            model.getDriveComponent().upperLeft.setPower(this.power);
            model.getDriveComponent().lowerRight.setPower(this.power);


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
        }

    }
    @Override
    public void update(){
        power = getPowerLevel(model.getDriveComponent().lowerRight.getCurrentPosition(),ticksToPosition);
        if (direction == MotionDirection.vertical) {
            model.getDriveComponent().lowerLeft.setPower(power);
            model.getDriveComponent().upperRight.setPower(power);
        }else {
            model.getDriveComponent().lowerLeft.setPower(-power);
            model.getDriveComponent().upperRight.setPower(-power);
        }
        model.getDriveComponent().upperLeft.setPower(power);
        model.getDriveComponent().lowerRight.setPower(power);
    }
    double getPowerLevel(int currentTicks,int ticksToPosition) {
        double min_power = 0.5;
        double max_power = 1;
        double tickDifference = ticksToPosition - currentTicks;
        double powerLevel = min_power +(tickDifference*3/ ticksToPosition) * (max_power - min_power);

        // Make sure the power level is within the valid range of 0.5 to 1.0
        powerLevel = Math.max(powerLevel, min_power);
        powerLevel = Math.min(powerLevel, max_power);

        return powerLevel;
    }

}
