package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class UngrabAction extends BaseAction {
    RobotModel model;

    ArmComponent armComponent;

    public UngrabAction(RobotModel model, ArmComponent armComponent) {
        this.model = model;
        this.armComponent = armComponent;
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
