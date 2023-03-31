package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

@TeleOp
public class TestMode extends OpMode {
    RobotModel model;
    ITest currentTest;
    @Override
    public void init() {
        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        //GyroSensor gyroSensor = hardwareMap.get(GyroSensor.class, "gyro");
        DriveComponent driveComponent = new DriveComponent(upperLeft, lowerLeft, upperRight, lowerRight, null);
        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo rClaw = hardwareMap.get(Servo.class, "rightClaw");
        Servo lClaw = hardwareMap.get(Servo.class, "leftClaw");
        ArmComponent armComponent = new ArmComponent(armMotor, rClaw, lClaw);

        model = new RobotModel(driveComponent, armComponent, new Coordinates(0, 0),
                0, true);

    }

    @Override
    public void loop() {
        if(currentTest != null) {
            currentTest.update();
            if(currentTest.isFinished())
                currentTest = null;
        } else {
            if(gamepad1.cross) {
                currentTest = new HorizontalMovementTest(model, telemetry);
                currentTest.start();
            }

            if(gamepad1.circle) {
                currentTest = new VerticalMovementTest(model, telemetry);
                currentTest.start();
            }

        }

    }
}
