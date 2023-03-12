package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.units.qual.C;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RGBColors;


public class AutonomousOpMode {
    Telemetry telemetry;
    HardwareMap hardwareMap;
    RGBColors color;
    RobotController controller;
    boolean firstIteration = true;

    public void init(RobotModel model, FieldModel fieldModel, Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;


        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        DriveComponent driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo cleshnja = hardwareMap.get(Servo.class, "grapler");
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "clr");
        ArmComponent armComponent = new ArmComponent(armMotor, cleshnja, colorSensor);


        controller = new RobotController(model, fieldModel, driveComponent, armComponent, telemetry);
    }


    public void loop() {
        if(firstIteration) {
              //controller.testMoveToPositionAction(new Coordinates(50, 50));
              controller.start();
              firstIteration = false;
        } else {
            controller.update();
        }
        telemetry.update();
    }
}
