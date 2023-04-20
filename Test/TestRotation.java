package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.TurnAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;
@Autonomous

public class TestRotation extends OpMode {
    RobotModel robotModel;
    IAction rotation;

    @Override
    public void init() {
//        robotModel = new RobotModel(hardwareMap);
//        rotation = new TurnAction(robotModel, 90, telemetry);
//        rotation.start();
    }

    @Override
    public void loop() {
        if(!rotation.isFinished())
            rotation.update();

    }
}
