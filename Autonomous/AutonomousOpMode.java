package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.FigureOutParkingPositionAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;
import org.firstinspires.ftc.teamcode.Common.ArmPosition;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.DriveComponent;
import org.firstinspires.ftc.teamcode.Common.RGBColors;

@TeleOp
public class AutonomousOpMode extends OpMode {
    RGBColors color;
    RobotController controller;
    boolean firstIteration = true;
    @Override
    public void init() {
        RobotModel model = new RobotModel(new Coordinates(33/2,38.3/2), 90, ArmPosition.Zero, true);
        FieldModel fieldModel = new FieldModel(new Coordinates(33/2,38.3/2), //starting position
                                               new Coordinates(0, 1.5*61), //colored cone vector
                                               new Coordinates(-1*61, 1*61), //red parking position vector
                                               new Coordinates(0, 1*61), //green parking position vector
                                               new Coordinates(1*61, 1*61)); //blue parking position vector

        DcMotor upperLeft = hardwareMap.get(DcMotor.class, "lfw");
        DcMotor upperRight = hardwareMap.get(DcMotor.class, "rfw");
        DcMotor lowerLeft = hardwareMap.get(DcMotor.class, "lbw");
        DcMotor lowerRight = hardwareMap.get(DcMotor.class, "rbw");
        DriveComponent driveComponent = new DriveComponent(upperLeft, upperRight, lowerLeft, lowerRight);

        DcMotor armMotor = hardwareMap.get(DcMotor.class, "lift");
        Servo cleshnja = hardwareMap.get(Servo.class, "grapler");
        ColorSensor colorSensor = hardwareMap.get(ColorSensor.class, "clr");
        ArmComponent armComponent = new ArmComponent(armMotor, cleshnja, colorSensor);

        controller = new RobotController(model, fieldModel, driveComponent, armComponent);
        controller.setTelemetry(telemetry);
    }

    @Override
    public void loop() {
        //if(gamepad1.cross == true) {
            //controller.testMoveToPositionAction(new Coordinates(-50, -50));
        //}
        if(firstIteration) {
              controller.start();
              firstIteration = false;
        } else {
            controller.update();
        }
        telemetry.update();
    }
}
