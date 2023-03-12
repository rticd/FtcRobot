package org.firstinspires.ftc.teamcode.Autonomous.Actions;

public class WaitAction extends BaseAction {
    int waitForMs;
    long startMs;

    public WaitAction(int waitForMs) {
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
}
