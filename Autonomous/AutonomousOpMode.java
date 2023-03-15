package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;


public class AutonomousOpMode {
    Telemetry telemetry;
    HardwareMap hardwareMap;
    RobotController controller;

    boolean firstIteration = true;

    public void init(RobotModel model, FieldModel fieldModel, Telemetry telemetry, HardwareMap hardwareMap) {
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        //GyroSensor gyroSensor = hardwareMap.get(GyroSensor.class, "gyro");
        DriveComponent driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight, null);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo cleshnja = hardwareMap.get(Servo.class, "grapler");
        ArmComponent armComponent = new ArmComponent(armMotor, cleshnja);

        controller = new RobotController(model, fieldModel, driveComponent, armComponent, telemetry);
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
