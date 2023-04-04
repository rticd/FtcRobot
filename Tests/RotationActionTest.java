package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.RotateAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
@Autonomous
public class RotationActionTest extends OpMode {
    RobotModel model;
    IAction rotationAction;

    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), 0, true);
        rotationAction = new RotateAction(model, 90, telemetry);
        rotationAction.start();
    }

    public void loop() {
        telemetry.addData("Rotation action finished:", rotationAction.isFinished());
        if(!rotationAction.isFinished())
            rotationAction.update();
    }
}
