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
    RobotController controller;
    RobotModel model;
    FieldModel fieldModel;

    boolean firstIteration = true;

    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), Math.PI/2, true);
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
