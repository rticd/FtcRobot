package org.firstinspires.ftc.teamcode.Autonomous.Actions;

import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.Actions.IAction;
import org.firstinspires.ftc.teamcode.Autonomous.RobotModel;
import org.firstinspires.ftc.teamcode.Common.ArmComponent;

public class GrabAction extends BaseAction {
    RobotModel model;
    ArmComponent armComponent;

    public GrabAction(RobotModel model, ArmComponent armComponent, Telemetry telemetry)  {
        super(telemetry);
        this.model = model;
        this.armComponent = armComponent;
    }
    @Override
    public void start() {
        armComponent.cleshnja.setPosition(1);
    }

    @Override
    public void update() {
        if(armComponent.cleshnja.getPosition() == 1) {
            model.grabberOpen = true;
            finished = true;
        }
    }

    @Override
    public void exit() {

    }
}
