package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;

@TeleOp
public class AutonomousOpMode extends OpMode {
    RobotController controller;
    @Override
    public void init() {
        RobotModel model = new RobotModel(new Coordinates(0,0), 180, ArmPosition.Zero, true);

        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        DriveComponent driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo cleshnja = hardwareMap.get(Servo.class, "grapler");
        ArmComponent armComponent = new ArmComponent(armMotor, cleshnja);

        controller = new RobotController(model, driveComponent, armComponent);
        controller.setTelemetry(telemetry);
    }

    @Override
    public void loop() {
        if(gamepad1.circle)
            controller.testArmControl(ArmPosition.Zero);
        if(gamepad1.square)
            controller.testArmControl(ArmPosition.First);
        if(gamepad1.cross)
            controller.testArmControl(ArmPosition.Second);
        if(gamepad1.triangle)
            controller.testArmControl(ArmPosition.Third);
        if(gamepad1.right_bumper)
            controller.testGraplerClose();
        if(gamepad1.left_bumper)
            controller.testGraplerOpen();
        if(gamepad1.right_trigger == 1)
            controller.testMovement();
        controller.update();
    }
}
