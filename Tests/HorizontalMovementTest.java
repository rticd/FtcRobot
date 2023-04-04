package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.LiftArmAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveHorizontallyAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveVerticallyAction;
import org.firstinspires.ftc.teamcode.Common.Coordinates;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

@Autonomous
public class HorizontalMovementTest extends OpMode {

    boolean firstIteration;
    RobotModel model;
    IAction horizontalAction;
    @Override
    public void init() {
        model = new RobotModel(hardwareMap, new Coordinates(0, 0), Math.PI/4, true);
    }

    @Override
    public void loop() {
        if(horizontalAction == null && gamepad1.right_bumper == true) {
            horizontalAction = new MoveHorizontallyAction(model, 0.3, -50, telemetry);
            horizontalAction.start();
        }
        else if(horizontalAction != null) {
            horizontalAction.update();
            telemetry.addData("action finished:", horizontalAction.isFinished());
        }
        telemetry.addData("x:", model.coordinates.getX());
        telemetry.addData("y:", model.coordinates.getY());
        telemetry.update();
    }
}
