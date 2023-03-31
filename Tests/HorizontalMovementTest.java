package org.firstinspires.ftc.teamcode.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.MoveHorizontallyAction;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public class HorizontalMovementTest implements ITest {
    Telemetry telemetry;
    RobotModel model;

    boolean finished;
    IAction movementAction;

    public HorizontalMovementTest(RobotModel model, Telemetry telemetry) {
        this.model = model;
        this.telemetry = telemetry;
    }

    @Override
    public void start() {
        movementAction = new MoveHorizontallyAction(model, 1, 50, telemetry);
        movementAction.start();
    }

    @Override
    public void update() {
        if(!movementAction.isFinished()) {
            movementAction.update();
        } else
            finished = true;
    }

    @Override
    public boolean isFinished() {
        return finished;
    }
}
