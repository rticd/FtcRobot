package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Common.Actions.IAction;
import org.firstinspires.ftc.teamcode.Common.Actions.TickMotionAction;
import org.firstinspires.ftc.teamcode.Common.MotionDirection;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

@Autonomous
public class MovementTest extends OpMode {
    RobotModel robotModel;
    IAction moveVerticallyAction;

    @Override
    public void init() {
//        robotModel = new RobotModel(hardwareMap);
//        moveVerticallyAction = new TickMotionAction(robotModel, 1, 50,
//                MotionDirection.horizontal, telemetry);
//        moveVerticallyAction.start();
    }

    @Override
    public void loop() {
        if(!moveVerticallyAction.isFinished())
            moveVerticallyAction.update();
        telemetry.addData("x", robotModel.coordinates.getX());
        telemetry.addData("y", robotModel.coordinates.getY());
        telemetry.update();
    }
}
