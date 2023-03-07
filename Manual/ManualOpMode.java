package org.firstinspires.ftc.teamcode.Manual;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
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
            telemetry.addData("currentGrapplerPosition", armComponent.cleshnja.getPosition());
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
        cleshnja.getController().pwmEnable();
        cleshnja.setPosition(1);
        cleshnja.setDirection(Servo.Direction.FORWARD);
        telemetry.addData("currentGrapplerPosition", cleshnja.getPosition());
        armComponent = new ArmComponent(armMotor, cleshnja);
        armController = new ManualArm(armComponent);
        armController.setTelemetry(telemetry);

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

        boolean clesnhjaClosed = gamepad2.right_bumper;
        telemetry.addData("closed", clesnhjaClosed);
        if(clesnhjaClosed)
            armController.setCleshnjaOpen(false);
        else
            armController.setCleshnjaOpen(true);

        armController.update();
    }
}
