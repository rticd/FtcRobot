package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public interface IAction {
    void start();
    void update();
    void exit();
    boolean isFinished();
}
