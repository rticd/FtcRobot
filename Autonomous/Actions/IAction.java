package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IAction {

    void setTelemetry(Telemetry telemetry);
    void start();
    void update();
    boolean isFinished();
}
