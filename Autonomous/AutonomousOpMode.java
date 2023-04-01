package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

@Autonomous
public class AutonomousOpMode extends OpMode {
    Telemetry telemetry;
    HardwareMap hardwareMap;
    RobotController controller;
    RobotModel model;
    FieldModel fieldModel;

    boolean firstIteration = true;

    @Override
    public void init() {
        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        //GyroSensor gyroSensor = hardwareMap.get(GyroSensor.class, "gyro");
        DriveComponent driveComponent = new DriveComponent(upperLeft, lowerLeft, upperRight, lowerRight, null,null);
        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo rClaw = hardwareMap.get(Servo.class, "rightClaw");
        Servo lClaw = hardwareMap.get(Servo.class, "leftClaw");
        ArmComponent armComponent = new ArmComponent(armMotor, rClaw, lClaw);

        model = new RobotModel(driveComponent, armComponent, null,new Coordinates(0, 0), Math.PI/2, true);
        fieldModel = new FieldModel(new Coordinates(0, 0));
        controller = new RobotController(model, fieldModel, telemetry);
    }


    public void loop() {
        if(firstIteration) {
              controller.start();
              firstIteration = false;
        } else {
            controller.update();
        }
        telemetry.update();
    }
}
