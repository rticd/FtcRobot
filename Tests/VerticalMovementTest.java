package org.firstinspires.ftc.teamcode.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveVerticallyAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class VerticalMovementTest implements ITest{
    Telemetry telemetry;
    RobotModel model;

    boolean finished;
    IAction movement;

    public VerticalMovementTest(RobotModel model, Telemetry telemetry) {
        this.model = model;
        this.telemetry = telemetry;
    }

    @Override
    public void start() {
        movement = new MoveVerticallyAction(model, 0.1, 50, telemetry);
        movement.start();
    }

    @Override
    public void update() {
        if(!movement.isFinished()) {
            movement.update();
        } else
            finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
