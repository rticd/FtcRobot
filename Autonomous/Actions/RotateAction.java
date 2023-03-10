package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

//Не законченно
public class RotateAction extends BaseAction
{
    int previousTicks;
    RobotModel model;

    DriveComponent driveComponent;

    double deltaAngle;
    public double getDeltaAngle() {
        return deltaAngle;
    }


    public RotateAction(RobotModel model, DriveComponent driveComponent, double deltaAngle) {
        this.model = model;
        this.driveComponent = driveComponent;
        this.deltaAngle = deltaAngle;
    }

    @Override
    public void start() {
        if(finished) return;
        driveComponent.lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveComponent.lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveComponent.upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        driveComponent.upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        driveComponent.lowerRight.setPower(1);
        driveComponent.upperRight.setPower(1);
        driveComponent.lowerLeft.setPower(1);
        driveComponent.upperLeft.setPower(1);

        double cmToRotate = deltaAngle / driveComponent.DEGREES_PER_CM_OF_ROTATION;
        int ticksToRotate = (int)(driveComponent.TICKS_PER_CM * cmToRotate);
        driveComponent.upperLeft.setTargetPosition(ticksToRotate);
        driveComponent.lowerLeft.setTargetPosition(-ticksToRotate);
        driveComponent.upperRight.setTargetPosition(ticksToRotate);
        driveComponent.lowerRight.setTargetPosition(-ticksToRotate);

        driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    @Override
    public void update() {
        //telemetry.addData("rotation finished", finished);
        if(finished) return;
        double cmToRotate = deltaAngle / driveComponent.DEGREES_PER_CM_OF_ROTATION;
        //telemetry.addData("cmToRotate ", cmToRotate);
        int ticksToRotate = (int)(driveComponent.TICKS_PER_CM * cmToRotate);
        int currentTicks = driveComponent.upperLeft.getCurrentPosition();
        double currentCm = currentTicks / driveComponent.TICKS_PER_CM;
        //telemetry.addData("currentCm ", currentCm);
        double currentDeltaAngle = currentCm * driveComponent.DEGREES_PER_CM_OF_ROTATION;
        //telemetry.addData("currentDelta angle", currentDeltaAngle);
        //telemetry.addData("currentAbs angle", model.absAngle);
        model.absAngle += currentDeltaAngle;
        //May need to check if it's actually finished moving
        //telemetry.addData("currentTicks ", currentTicks);
        //telemetry.addData("targetTicks ", ticksToRotate);
        if(currentTicks == ticksToRotate && currentTicks == previousTicks) {
            finished = true;
        }
        previousTicks = currentTicks;
    }


}
