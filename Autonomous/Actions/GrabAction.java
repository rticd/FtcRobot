package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class GrabAction extends BaseAction {
    RobotModel model;

    ArmComponent armComponent;

    public GrabAction(RobotModel model, ArmComponent armComponent) {
        this.model = model;
        this.armComponent = armComponent;
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
