package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveHorizontallyAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveVerticallyAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

@Autonomous
public class VerticalMovementTest extends OpMode {
    RobotModel model;
    IAction verticalAction;
    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), Math.PI/4, true);

    }

    @Override
    public void loop() {
        if(verticalAction == null && gamepad1.right_bumper == true) {
            verticalAction = new MoveVerticallyAction(model, 0.4, 50, telemetry);
            verticalAction.start();
        }
        else if(verticalAction != null) {
            verticalAction.update();
            telemetry.addData("action finished:", verticalAction.isFinished());
        }
        telemetry.addData("x:", model.coordinates.getX());
        telemetry.addData("y:", model.coordinates.getY());
        telemetry.update();
    }
}
