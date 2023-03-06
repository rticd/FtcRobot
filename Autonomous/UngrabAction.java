package org.firstinspires.ftc.teamcode.Autonomous;

import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class UngrabAction implements IAction {
    RobotModel model;

    ArmComponent armComponent;

    boolean finished;
    @Override
    public boolean isFinished() {
        return finished;
    }

    public UngrabAction(RobotModel model, ArmComponent armComponent) {

    }


    @Override
    public void start() {
        armComponent.cleshnja.setPosition(0);
    }

    @Override
    public void update() {
        //Осторожно тут с getPosition. Нужно проверить телеметрией.
        if(armComponent.cleshnja.getPosition() == 0) {
            model.grabberOpen = false;
            finished = true;
        }

    }
}
