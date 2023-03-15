package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;

public class NoAction extends BaseAction {

    public NoAction(Telemetry telemetry) {
        super(telemetry);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {

    }

    @Override
    public void exit() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
