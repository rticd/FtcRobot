package org.firstinspires.ftc.teamcode.Manual;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Common.ArmMode;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RotationDirection;
import org.firstinspires.ftc.teamcode.Common.SensorComponent;
import org.firstinspires.ftc.teamcode.Common.Turn;

@TeleOp
public class ManualOpMode extends LinearOpMode {

    DriveComponent driveComponent;
    ArmComponent armComponent;

    SensorComponent sensorComponent;

    RobotModel model;

    ManualDrive movementController;
    ManualArm armController;

    RotationDirection rot  = RotationDirection.none;

    @Override
    public void runOpMode() throws InterruptedException {
        initialize();
        while(true) {
            driveControl();
            armControl();
            rotate();
            telemetry.update();
        }
    }

    void initialize() {
        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        DistanceSensor sensorRange = hardwareMap.get(DistanceSensor.class, "dist");

        //gyro
        BNO055IMU imu = hardwareMap.get(BNO055IMU.class, "gyro");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "Calibration.json";
        imu.initialize(parameters);



        driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight, imu, sensorRange);

        sensorComponent = new SensorComponent(imu,sensorRange);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo rightClaw = hardwareMap.get(Servo.class, "rightClaw");
        Servo leftClaw = hardwareMap.get(Servo.class, "leftClaw");

        rightClaw.getController().pwmEnable();

        rightClaw.setDirection(Servo.Direction.FORWARD);
        leftClaw.getController().pwmEnable();
        leftClaw.setPosition(0);
        rightClaw.setPosition(0);
        leftClaw.setDirection(Servo.Direction.FORWARD);
        armComponent = new ArmComponent(armMotor, rightClaw, leftClaw);
        model = new RobotModel(driveComponent, armComponent,sensorComponent, new Coordinates(0, 0),
                0, true);
        armController = new ManualArm(model);
        movementController = new ManualDrive(model);

    }

    void Stop() {
        //armComponent.rightClaw.setPosition(0);
        //armComponent.leftClaw.setPosition(0);
        driveComponent.lowerLeft.setPower(0);
        driveComponent.upperLeft.setPower(0);
        driveComponent.lowerRight.setPower(0);
        driveComponent.upperRight.setPower(0);
    }
    void driveControl() {
        if( Math.abs(gamepad1.left_stick_x) > 0.01 || Math.abs(gamepad1.left_stick_y) > 0.01 ||
                Math.abs(gamepad1.right_stick_x) > 0.01 || Math.abs(gamepad1.right_stick_y) > 0.01){
            double turn = gamepad1.right_stick_x;
            double y = gamepad1.left_stick_x;
            double x = -gamepad1.left_stick_y;
            movementController.setTurn(turn);
            movementController.setX(x);
            movementController.setY(y);
            movementController.update();
        } else {
            Stop();
        }
    }

    void armControl() {
            if(gamepad2.circle) {
                armController.setPosition(ArmPosition.Zero);
                armController.mode = ArmMode.auto;
            }
            if(gamepad2.triangle) {
                armController.setPosition(ArmPosition.First);
                armController.mode = ArmMode.auto;
            }
            if(gamepad2.square) {
                armController.setPosition(ArmPosition.Second);
                armController.mode = ArmMode.auto;
            }
            if(gamepad2.cross){
                armController.setPosition(ArmPosition.Third);
                armController.mode = ArmMode.auto;
            }

            if (armController.mode!=ArmMode.auto){
                if (gamepad2.left_trigger>0.1f){
                    armController.indefinite(1);
                    armController.mode = ArmMode.manual;
                } else if (gamepad2.right_trigger >0.1f){
                    armController.indefinite(-1);
                    armController.mode = ArmMode.manual;
                } else{
                    armController.indefinite(0);
                    armController.mode = ArmMode.manual;
                }
            }
            if (gamepad2.dpad_left){
                armController.mode=ArmMode.manual;
            }

       /* if(gamepad2.right_bumper)
            armController.setCleshnja(true);
        else if(gamepad2.left_bumper)
            armController.setCleshnja(false);

        */
        telemetry.addData("ArmMode",armController.mode);

        armController.update();
    }
    void rotate(){
        if (gamepad1.left_bumper && rot== RotationDirection.none){
            rot= Turn.turn(driveComponent.imu, -90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        } else if (gamepad1.right_bumper && rot==RotationDirection.none){
            rot=Turn.turn(driveComponent.imu,90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        }
        if(rot==RotationDirection.left){
            rot=Turn.turn(driveComponent.imu,  -90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        } else if (rot==RotationDirection.right) {
            rot=Turn.turn(driveComponent.imu, 90,telemetry, driveComponent);
            telemetry.addData("Pressed",rot);
        };
    }
}
