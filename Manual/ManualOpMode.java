package org.firstinspires.ftc.teamcode.Manual;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
@TeleOp
public class ManualOpMode extends LinearOpMode {

    DriveComponent driveComponent;
    ArmComponent armComponent;
    RobotModel model;

    ManualDrive movementController;
    ManualArm armController;


    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        while(true) {
            driveControl();
            armControl();
            telemetry.update();
        }
    }

    void initialize() {
        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight);
        movementController = new ManualDrive(driveComponent);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo cleshnja = hardwareMap.get(Servo.class, "grapler");
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "clr");
        cleshnja.getController().pwmEnable();
        cleshnja.setPosition(1);
        cleshnja.setDirection(Servo.Direction.FORWARD);
        armComponent = new ArmComponent(armMotor, cleshnja, colorSensor);
        armController = new ManualArm(armComponent);
        armController.setTelemetry(telemetry);

    }

    void onStop() {
        armComponent.cleshnja.setPosition(0);
    }
    void driveControl() {
        double turn = -gamepad1.right_stick_x;
        double x = -gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        movementController.setTurn(turn);
        movementController.setX(x);
        movementController.setY(y);
        movementController.update();
    }

    void armControl() {
        if(gamepad2.circle)
            armController.setPosition(ArmPosition.Zero);
        if(gamepad2.triangle)
            armController.setPosition(ArmPosition.First);
        if(gamepad2.square) {
            armController.setPosition(ArmPosition.Second);
        }
        if(gamepad2.cross){
            armController.setPosition(ArmPosition.Third);
        }

        if(gamepad2.right_bumper)
            armController.setCleshnjaOpen(false);
        else if(gamepad2.left_bumper)
            armController.setCleshnjaOpen(true);
        armController.update();
    }
}
