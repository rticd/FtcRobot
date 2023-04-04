package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
@Autonomous

public class ClawTest extends OpMode
{
    RobotModel model;

    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), 0, true);
    }

    @Override
    public void loop() {
        //Close
        if(gamepad1.right_bumper) {
            model.getArmComponent().rightClaw.setPosition(0);
            model.getArmComponent().leftClaw.setPosition(0);
            //Open
        } else if(gamepad1.left_bumper) {
            model.getArmComponent().rightClaw.setPosition(0.5);
            model.getArmComponent().leftClaw.setPosition(0.5);
        }

    }
}
