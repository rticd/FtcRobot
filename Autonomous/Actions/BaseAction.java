package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAction implements IAction {

    protected boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }
}
