package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class WaitAction extends BaseAction {
    int waitForMs;
    long startMs;

    public WaitAction(int waitForMs, Telemetry telemetry) {
        super(telemetry);
        this.waitForMs = waitForMs;

    }
    @Override
    public void start() {
        if(!finished) {
            startMs = System.currentTimeMillis();
        }
    }
    @Override
    public void update() {
        if(!finished) {
            if(System.currentTimeMillis() >= startMs + waitForMs)
                finished = true;
        }
    }

    @Override
    public void exit() {

    }
}
