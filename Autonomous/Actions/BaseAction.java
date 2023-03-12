package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAction implements IAction {

    Telemetry telemetry;
    @Override
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    protected boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }
}
