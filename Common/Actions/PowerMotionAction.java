package org.firstinspires.ftc.teamcode.Common.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.Components.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;


public class PowerMotionAction extends BaseAction {
    double power;
    MotionDirection direction;
    Coordinates initialCoordinates;
    public PowerMotionAction(RobotModel robotModel, double power, MotionDirection direction, Telemetry telemetry){
        super(robotModel,telemetry);
        this.power = power;
        this.direction = direction;


    }
    public void start(){
        this.initialCoordinates = initialCoordinates;
        DriveComponent motors = robotModel.getDriveComponent();
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
        } else{
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
    @Override
    public void update(){
        //Coordinates update
        int currentTicks = robotModel.getDriveComponent().upperLeft.getCurrentPosition();
        float cmTraveled = currentTicks / robotModel.getDriveComponent().TICKS_PER_CM;


        if(direction == MotionDirection.vertical) {
            //updating model
            Coordinates vector = new Coordinates(0, 0);
            //Horizontal vector is perpendicular to the vertical, so +90 degrees.
            vector = new Coordinates(cmTraveled * Math.cos(robotModel.absAngle), cmTraveled * Math.sin(robotModel.absAngle));
            robotModel.coordinates = Coordinates.add(initialCoordinates, vector);
            Coordinates targetCoordinates = Coordinates.add(initialCoordinates, vector);
            robotModel.coordinates = targetCoordinates;
        } else if(direction == MotionDirection.horizontal){
            //updating model
            Coordinates vector = new Coordinates(0, 0);
            //Horizontal vector is perpendicular to the vertical, so +90 degrees.
            vector = new Coordinates(cmTraveled * Math.cos(robotModel.absAngle + 90), cmTraveled * Math.sin(robotModel.absAngle + 90));
            robotModel.coordinates = Coordinates.add(initialCoordinates, vector);
            Coordinates targetCoordinates = Coordinates.add(initialCoordinates, vector);
            robotModel.coordinates = targetCoordinates;
        }
    }
}
