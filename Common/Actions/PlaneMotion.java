package org.firstinspires.ftc.teamcode.Common.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Actions.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.Component.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;


public class PlaneMotion extends BaseAction{
    RobotModel model;
    double power;
    MotionDirection direction;
    public PlaneMotion(RobotModel model, double power, MotionDirection direction,Telemetry telemetry){
        super(model,telemetry);
        this.model = model;
        this.power = power;
        this.direction = direction;


    }
    public void start(){

    }
    @Override
    public void update(){
        DriveComponent motors = model.getDriveComponent();
        if (direction == MotionDirection.vertical){
            motors.lowerLeft.setPower(power);
            motors.upperRight.setPower(power);
            motors.upperLeft.setPower(power);
            motors.lowerRight.setPower(power);
        } else if (direction == MotionDirection.horizontal){
            motors.lowerLeft.setPower(-power);
            motors.upperRight.setPower(-power);
            motors.upperLeft.setPower(power);
            motors.lowerRight.setPower(power);
        }else{
            motors.lowerLeft.setPower(0);
            motors.upperRight.setPower(0);
            motors.upperLeft.setPower(0);
            motors.lowerRight.setPower(0);
        }
        motors.upperLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.lowerLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.upperRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motors.lowerRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
