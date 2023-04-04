package org.firstinspires.ftc.teamcode.Autonomous.Actions;
public interface IAction {
    void start();
    void update();
    void exit();
    boolean isFinished();
}
