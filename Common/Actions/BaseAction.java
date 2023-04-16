package org.firstinspires.ftc.teamcode.Common.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Common.RobotModel;

public abstract class BaseAction implements Action {
    RobotModel model;
    Telemetry telemetry;
    public BaseAction(RobotModel model, Telemetry telemetry) {
        this.model = model;
        this.telemetry = telemetry;
    }
    protected boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }
}
