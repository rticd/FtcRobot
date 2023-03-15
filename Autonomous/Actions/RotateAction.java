package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

//Переписать с gyro
public class RotateAction extends BaseAction
{
    double initialAbsAngle;
    int previousTicks;
    RobotModel model;

    DriveComponent driveComponent;

    double absAngle;
    public double getAbsAngle() {
        return absAngle;
    }

    double deltaAngle;
    public double getDeltaAngle() {
        return deltaAngle;
    }


    public RotateAction(RobotModel model, DriveComponent driveComponent, double absAngle,
                        Telemetry telemetry) {
        super(telemetry);
        this.model = model;
        this.driveComponent = driveComponent;
        this.absAngle = absAngle;
    }

    @Override
    public void start() {
        if(finished) return;
//        driveComponent.lowerRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        driveComponent.lowerLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        driveComponent.upperRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        driveComponent.upperLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        driveComponent.lowerRight.setPower(1);
//        driveComponent.upperRight.setPower(1);
//        driveComponent.lowerLeft.setPower(1);
//        driveComponent.upperLeft.setPower(1);
//
//
//        deltaAngle = absAngle - model.absAngle;
//        double cmToRotate = deltaAngle / driveComponent.DEGREES_PER_CM_OF_ROTATION;
//        int ticksToRotate = (int)(driveComponent.TICKS_PER_CM * cmToRotate);
//        driveComponent.upperLeft.setTargetPosition(ticksToRotate);
//        driveComponent.lowerLeft.setTargetPosition(-ticksToRotate);
//        driveComponent.upperRight.setTargetPosition(ticksToRotate);
//        driveComponent.lowerRight.setTargetPosition(-ticksToRotate);
//
//        driveComponent.upperLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        driveComponent.lowerLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        driveComponent.upperRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        driveComponent.lowerRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        initialAbsAngle = model.absAngle;
    }

    @Override
    public void update() {
        if(!finished)
        {
//            double cmToRotate = deltaAngle / driveComponent.DEGREES_PER_CM_OF_ROTATION;
//        int ticksToRotate = (int)(driveComponent.TICKS_PER_CM * cmToRotate);
//        int currentTicks = driveComponent.upperLeft.getCurrentPosition();
//        double currentCm = currentTicks / driveComponent.TICKS_PER_CM;
//        double currentDeltaAngle = currentCm * driveComponent.DEGREES_PER_CM_OF_ROTATION;
//        model.absAngle = initialAbsAngle + currentDeltaAngle;
//
//        if(currentTicks == ticksToRotate && currentTicks == previousTicks) {
//            finished = true;
//        }
//        previousTicks = currentTicks;
        }

    }

    @Override
    public void exit() {

    }


}
