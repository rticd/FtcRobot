package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class GrabAction implements IAction {
    RobotModel model;

    ArmComponent armComponent;

    boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }

    public GrabAction(RobotModel model, ArmComponent armComponent) {

    }


    @Override
    public void start() {
        armComponent.cleshnja.setPosition(1);
    }

    @Override
    public void update() {
        //Осторожно тут с getPosition. Нужно проверить телеметрией.
        if(armComponent.cleshnja.getPosition() == 1) {
            model.grabberOpen = true;
            finished = true;
        }

    }
}
